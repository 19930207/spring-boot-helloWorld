# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

```bash
# Compile
mvn compile

# Run (requires MySQL, see below)
mvn spring-boot:run

# Package
mvn package -DskipTests
```

## Database

MySQL database `tools` is required. Configure credentials in `src/main/resources/application.yml`.

Tables are `tool` and `user`. MyBatis-Plus `BaseMapper` handles all SQL — no manual XML mappers. Entities use `@TableField(fill = FieldFill.INSERT/INSERT_UPDATE)` for automatic `create_time`/`update_time` timestamps via `MyBatisPlusConfig.java`.

## Architecture

Standard Spring Boot layered architecture under `com.neo`:

- **Controller** → **Service** → **Mapper** (MyBatis-Plus `BaseMapper`)
- `Result<T>` wraps all API responses: `code`, `message`, `data`
- `MybatisPlusInterceptor` with `PaginationInnerInterceptor` enables `Page<T>` across all list endpoints

### Key Design Decisions

- **User password**: `@TableField(select = false)` so `selectById` / `selectPage` never returns the password field
- **Status fields**: both `tool.status` (1=online, 0=offline) and `user.status` (1=enabled, 0=disabled) use integer flags — online/enable/offline/disable endpoints update only this field
- **No logical delete**: deletion is physical (`deleteById`)
- **Tool create** defaults `status=1` (online)

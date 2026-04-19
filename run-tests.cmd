@echo off
setlocal enabledelayedexpansion

if exist .env (
    for /f "usebackq tokens=1,* delims==" %%a in (".env") do (
        set "%%a=%%b"
    )
)

mvnw.cmd clean verify -Dplaywright.headless=%PLAYWRIGHT_HEADLESS% %*
endlocal
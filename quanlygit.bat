chcp 65001
color a
title Quản Lý Git
set dir=%~dp0
cd %dir%
echo off
cls

:run
echo Chọn chế độ(pull nhập 1, push nhập 2, reset branch nhập 3, set upstream nhập 4):
choice /c 1234 > nul
set "mode=%errorlevel%"
if "%mode%"=="1" goto pull
if "%mode%"=="2" goto push
if "%mode%"=="3" goto reset
if "%mode%"=="4" goto setup

:pull
set pullLog=
for /f "delims=" %%i in ('git.exe pull 2^>^&1') do (set pullLog=%%i)
if "x%pullLog:Already=%"=="x%pullLog%" if "x%pullLog:changed=%"=="x%pullLog%" goto pullErrHandle
echo Đã cập nhật về thiết bị
goto stop

:push
set /p msgpush=Nhập commit:
if not defined msgpush goto push
git.exe add .
git.exe commit -m "%msgpush%"
set pushLog=
for /f "delims=" %%i in ('git.exe push 2^>^&1') do (set pushLog=%%i)
if "x%pushLog:->=%"=="x%pushLog%" if "x%pushLog:up-to-date=%"=="x%pushLog%" goto pushErrHandle
echo Đã cập nhật lên Github
goto stop

:err
echo Lỗi, thử cách khác?
set /p select=(Y/N):
if "%select%"=="n" goto stop
if "%select%"=="N" goto stop
if "%select%"=="y" if "%mode%"=="1" goto pullErrHandle 
if "%select%"=="y" if "%mode%"=="2" goto pushErrHandle
if "%select%"=="Y" if "%mode%"=="1" goto pullErrHandle 
if "%select%"=="Y" if "%mode%"=="2" goto pushErrHandle
echo Nhập đúng định dạng
goto err

:pullErrHandle
echo Hãy commit code trước
set /p msgpull=Nhập commit:
if not defined msgpull goto pullErrHandle
git.exe add .
git.exe commit -m %msgpull%
git.exe pull
set pullLog=
for /f "delims=" %%i in ('git.exe pull 2^>^&1') do (set pullLog=%%i)
if "x%pullLog:Already=%"=="x%pullLog%" if "x%pullLog:changed=%"=="x%pullLog%" goto err
echo Đã cập nhật về thiết bị
goto stop

:pushErrHandle
echo Hãy nhập tên nhánh cần push(thông thường là main):
set /p branchName=Tên nhánh:
if not defined branchName goto pushErrHandle
git.exe pull
git.exe push origin %branchName%
set pushLog=
for /f "delims=" %%i in ('git.exe push 2^>^&1') do (set pushLog=%%i)
if "x%pushLog:->=%"=="x%pushLog%" if "x%pushLog:up-to-date=%"=="x%pushLog%" goto err
echo Đã cập nhật lên Github
goto stop

:reset
set /p commitId=Nhập commit id:
if not defined commitId goto reset
set /p branch=Nhập branch name:
git.exe reset "--hard" %commitId%
git.exe push -f origin %branch%
echo Đã reset về commit %id%, nhớ nhắc mọi người clone code mới
goto stop

:setup
git.exe branch --set-upstream-to=origin/main main

:stop
pause
exit
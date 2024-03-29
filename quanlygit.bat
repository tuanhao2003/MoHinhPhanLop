chcp 65001
color a
title Quản Lý Git
set dir=%~dp0
cd %dir%
echo off
cls

:run
echo ###########################################################################################################
echo #                                                                                                         #
echo # Chọn chế độ(init nhấn 0, pull nhấn 1, push nhấn 2, reset branch nhấn 3, fix setup stream error nhấn 4): #
echo #                                                                                                         #
echo ###########################################################################################################
choice /c 01234 > nul
set "mode=%errorlevel%"
if "%mode%"=="1" goto init
if "%mode%"=="2" goto pull
if "%mode%"=="3" goto push
if "%mode%"=="4" goto reset
if "%mode%"=="5" goto setup

:init
cls
echo #############
echo # init mode #
echo #############
git.exe init
git.exe
set /p originUrl=Nhập git url:
git.exe remote add origin "%originUrl%"
echo Đã kết nối với github %originUrl%
goto stop


:pull
cls
echo #############
echo # pull mode #
echo #############
set pullLog=
for /f "delims=" %%i in ('git.exe pull 2^>^&1') do (set pullLog=%%i)
if "x%pullLog:Already=%"=="x%pullLog%" if "x%pullLog:changed=%"=="x%pullLog%" goto pullErrHandle
echo Đã cập nhật về thiết bị
goto stop

:push
cls
echo #############
echo # push mode #
echo #############
set /p msgpush=Nhập commit:
if not defined msgpush goto push
git.exe add .
git.exe commit -m "%msgpush%"
set pushLog=
for /f "delims=" %%i in ('git.exe push 2^>^&1') do (set pushLog=%%i)
if "x%pushLog:->=%"=="x%pushLog%" if "x%pushLog:up-to-date=%"=="x%pushLog%" goto pushErrHandle
echo Đã cập nhật lên Github
goto stop


:reset
cls
echo ##############
echo # reset mode #
echo ##############
set /p commitId=Nhập commit id:
if not defined commitId goto reset
set /p branch=Nhập branch name:
git.exe reset "--hard" %commitId%
git.exe push -f origin %branch%
echo Đã reset về commit %id%, nhớ nhắc mọi người clone code mới
goto stop

:setup
cls
echo ############
echo # fix mode #
echo ############
echo Hãy nhập tên nhánh mặc định(thông thường là main):
set /p branchDefault=Tên nhánh:
git.exe branch --set-upstream-to=origin/main "%branchDefault%"

:err
echo #######################
echo # Lỗi, thử cách khác? #
echo #######################
echo (Y/N):
choice /c yn > nul
set "mode=%errorlevel%"
if "%mode%"=="1" goto run
if "%mode%"=="2" goto stop
echo Nhập đúng định dạng
goto err

:pullErrHandle
echo ##############################
echo # Lỗi! Hãy commit code trước #
echo ##############################
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
echo ############
echo # Lỗi push #
echo ############
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

:stop
pause
exit
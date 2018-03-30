@echo off
cls
@echo www.betcesc.com - Print local server executing
for %%X in (c:\pool\*.txt) do (
	@echo "Imprimir type %%X >lpt1:"
	type %%X >lpt1:
	ping 1.1.1.1 -n 1 -w 5000 >nul
	@echo "Eliminar"
	rem del "%%X" /f/q
)
ping 1.1.1.1 -n 1 -w 2000 >nul
@echo .
ping 1.1.1.1 -n 1 -w 2000 >nul
@echo .
ping 1.1.1.1 -n 1 -w 2000 >nul
@echo .
ping 1.1.1.1 -n 1 -w 2000 >nul
rem imprimir.bat

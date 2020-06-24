@echo off

echo Autodetecting Java...
set nL=                                                                                

if not exist "%ProgramFiles%\jre*\bin\java.exe" (
  if not exist "%ProgramFiles%\jre1.*\bin\java.exe" (
    if not exist "%ProgramFiles%\jdk1.*\bin\java.exe" (
      if not exist "%ProgramFiles%\Java\jre*\bin\java.exe" (
        if not exist "%ProgramFiles%\Java\jre1.*\bin\java.exe" (
          if not exist "%ProgramFiles%\Java\jdk1.*\bin\java.exe" (
            echo Java not found in a native folder. Checking compatability folders...
            if not exist "%ProgramFiles%\(x86)jre*\bin\java.exe" (
              if not exist "%ProgramFiles(x86)%\jre1.*\bin\java.exe" (
                if not exist "%ProgramFiles(x86)%\jdk1.*\bin\java.exe" (
                  if not exist "%ProgramFiles(x86)%\Java\jre*\java.exe" (
                    if not exist "%ProgramFiles(x86)%\Java\jre1.*\java.exe" (
                      if not exist "%ProgramFiles(x86)%\Java\jdk1.*\java.exe" (
echo Error message:%nL%%nL%%nL%%nL%%nL%%nL%
echo         ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿
echo         ³ ! Error - Blue Husky's Checkers 1.1.10                      ³±
echo         ÃÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ´±
echo         ³ ÉÍÍÍ»                                                       ³±
echo         ³ º ³ º  Java Runtime Environment not found.                  ³±
echo         ³ º . º  Did you read instructions / download it?             ³±
echo         ³ ÈÍÍÍ¼                                                       ³±
echo         ³          ÉÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ»         ³±
echo         ³          º Press any key to attempt to run anyway º         ³±
echo         ³          ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ¼         ³±
echo         ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ±
echo          ±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±%nL%%nL%%nL%%nL%%nL%%nL%
pause
                      )
                    )
                  )
                )
              )
            )
          )
        )
      )
    )
  )
)


if not exist Setup.class (
  echo Error message:
  echo %nL%%nL%%nL%%nL%%nL%%nL%
  echo         ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿
  echo         ³ ! Error - Blue Husky's Checkers 1.1.10                      ³±
  echo         ÃÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ´±
  echo         ³ ÉÍÍÍ»                                                       ³±
  echo         ³ º ³ º  Setup.class not found.                               ³±
  echo         ³ º . º  Did you extract all the files to the same folder?    ³±
  echo         ³ ÈÍÍÍ¼                                                       ³±
  echo         ³                  ÉÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ»                 ³±
  echo         ³                  º Press any key for help º                 ³±
  echo         ³                  ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ¼                 ³±
  echo         ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ±
  echo          ±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±%nL%%nL%%nL%%nL%%nL%%nL%
  pause
  Readme.txt
  exit
)

if errorlevel 1 (
  if not exist Setup.class (
    echo %nL%%nL%%nL%%nL%%nL%%nL%
    echo         ÚÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ¿
    echo         ³ ! Error - Blue Husky's Checkers 1.0.11                      ³±
    echo         ÃÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄ´±
    echo         ³ ÉÍÍÍ»                                                       ³±
    echo         ³ º ³ º  Required files not found.                            ³±
    echo         ³ º . º  Did you extract all the files to the same folder?    ³±
    echo         ³ ÈÍÍÍ¼                                                       ³±
    echo         ³                  ÉÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ»                 ³±
    echo         ³                  º Press any key for help º                 ³±
    echo         ³                  ÈÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍÍ¼                 ³±
    echo         ÀÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÄÙ±
    echo          ±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±%nL%%nL%%nL%%nL%%nL%%nL%
    pause
    Readme.txt
    exit
  )
)

java Setup
run
@echo off

echo Autodetecting Java...
set nL=                                                                                

if not exist "%ProgramFiles%\jre6\bin\java.exe" (
  if not exist "%ProgramFiles%\jre1.6.*\bin\java.exe" (
    if not exist "%ProgramFiles%\jdk1.6.*\bin\java.exe" (
      if not exist "%ProgramFiles%\Java\jre6\bin\java.exe" (
        if not exist "%ProgramFiles%\Java\jre1.6.*\bin\java.exe" (
          if not exist "%ProgramFiles%\Java\jdk1.6.*\bin\java.exe" (
            echo Java not found in a native folder. Checking compatability folders...
            if not exist "%ProgramFiles%\(x86)jre6\bin\java.exe" (
              if not exist "%ProgramFiles(x86)%\jre1.6.*\bin\java.exe" (
                if not exist "%ProgramFiles(x86)%\jdk1.6.*\bin\java.exe" (
                  if not exist "%ProgramFiles(x86)%\Java\jre6\java.exe" (
                    if not exist "%ProgramFiles(x86)%\Java\jre1.6.*\java.exe" (
                      if not exist "%ProgramFiles(x86)%\Java\jdk1.6.*\java.exe" (
echo Error message:%nL%%nL%%nL%%nL%%nL%%nL%
echo         旼컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴�
echo         � ! Error - Blue Husky's Checkers 1.1.10                      낢
echo         쳐컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴눙
echo         � �袴敲                                                       낢
echo         � � � �  Java Runtime Environment not found.                  낢
echo         � � . �  Did you read instructions / download it?             낢
echo         � 훤袴�                                                       낢
echo         �                  �袴袴袴袴袴袴袴袴袴袴袴袴�                 낢
echo         �                  � Press any key for help �                 낢
echo         �                  훤袴袴袴袴袴袴袴袴袴袴袴暠                 낢
echo         읕컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴袂
echo          굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇�%nL%%nL%%nL%%nL%%nL%%nL%
pause
Readme.txt
exit
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
  echo         旼컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴�
  echo         � ! Error - Blue Husky's Checkers 1.1.10                      낢
  echo         쳐컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴눙
  echo         � �袴敲                                                       낢
  echo         � � � �  Setup.class not found.                               낢
  echo         � � . �  Did you extract all the files to the same folder?    낢
  echo         � 훤袴�                                                       낢
  echo         �                  �袴袴袴袴袴袴袴袴袴袴袴袴�                 낢
  echo         �                  � Press any key for help �                 낢
  echo         �                  훤袴袴袴袴袴袴袴袴袴袴袴暠                 낢
  echo         읕컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴袂
  echo          굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇�%nL%%nL%%nL%%nL%%nL%%nL%
  pause
  Readme.txt
  exit
)

if errorlevel 1 (
  if not exist Setup.class (
    echo %nL%%nL%%nL%%nL%%nL%%nL%
    echo         旼컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴�
    echo         � ! Error - Blue Husky's Checkers 1.0.11                      낢
    echo         쳐컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴눙
    echo         � �袴敲                                                       낢
    echo         � � � �  Required files not found.                            낢
    echo         � � . �  Did you extract all the files to the same folder?    낢
    echo         � 훤袴�                                                       낢
    echo         �                  �袴袴袴袴袴袴袴袴袴袴袴袴�                 낢
    echo         �                  � Press any key for help �                 낢
    echo         �                  훤袴袴袴袴袴袴袴袴袴袴袴暠                 낢
    echo         읕컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴컴袂
    echo          굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇굇�%nL%%nL%%nL%%nL%%nL%%nL%
    pause
    Readme.txt
    exit
  )
)

java Setup
run
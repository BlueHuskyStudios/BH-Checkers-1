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
echo         �������������������������������������������������������������Ŀ
echo         � ! Error - Blue Husky's Checkers 1.1.10                      ��
echo         �������������������������������������������������������������Ĵ�
echo         � ���ͻ                                                       ��
echo         � � � �  Java Runtime Environment not found.                  ��
echo         � � . �  Did you read instructions / download it?             ��
echo         � ���ͼ                                                       ��
echo         �                  ������������������������ͻ                 ��
echo         �                  � Press any key for help �                 ��
echo         �                  ������������������������ͼ                 ��
echo         ��������������������������������������������������������������ٱ
echo          ���������������������������������������������������������������%nL%%nL%%nL%%nL%%nL%%nL%
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
  echo         �������������������������������������������������������������Ŀ
  echo         � ! Error - Blue Husky's Checkers 1.1.10                      ��
  echo         �������������������������������������������������������������Ĵ�
  echo         � ���ͻ                                                       ��
  echo         � � � �  Setup.class not found.                               ��
  echo         � � . �  Did you extract all the files to the same folder?    ��
  echo         � ���ͼ                                                       ��
  echo         �                  ������������������������ͻ                 ��
  echo         �                  � Press any key for help �                 ��
  echo         �                  ������������������������ͼ                 ��
  echo         ��������������������������������������������������������������ٱ
  echo          ���������������������������������������������������������������%nL%%nL%%nL%%nL%%nL%%nL%
  pause
  Readme.txt
  exit
)

if errorlevel 1 (
  if not exist Setup.class (
    echo %nL%%nL%%nL%%nL%%nL%%nL%
    echo         �������������������������������������������������������������Ŀ
    echo         � ! Error - Blue Husky's Checkers 1.0.11                      ��
    echo         �������������������������������������������������������������Ĵ�
    echo         � ���ͻ                                                       ��
    echo         � � � �  Required files not found.                            ��
    echo         � � . �  Did you extract all the files to the same folder?    ��
    echo         � ���ͼ                                                       ��
    echo         �                  ������������������������ͻ                 ��
    echo         �                  � Press any key for help �                 ��
    echo         �                  ������������������������ͼ                 ��
    echo         ��������������������������������������������������������������ٱ
    echo          ���������������������������������������������������������������%nL%%nL%%nL%%nL%%nL%%nL%
    pause
    Readme.txt
    exit
  )
)

java Setup
run
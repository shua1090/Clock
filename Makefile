run:link
	.\bin\main.exe

debug:link
	gdb .\bin\main.exe

link: 
	g++ src/main.cpp -o bin\main.exe -lsfml-graphics -lsfml-window -lsfml-system

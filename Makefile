.SILENT:

INCLUDE = -DSFML_STATIC -Iinclude
EXTERNAL = -lsfml-graphics-s -lsfml-window-s -lsfml-system-s -lopengl32 -lwinmm -lgdi32 -lfreetype
STANDARD = -static-libgcc -static-libstdc++
FILES = src/main.cpp

all: build run
build: compile link
compile:
	echo "Compiling CPP files..."
	g++ -c $(FILES) $(INCLUDE)
link:
	echo "Linking libraries..."
	g++ $(wildcard *.o) -o bin/main.exe $(STANDARD) -Llib $(EXTERNAL)
run:
	echo "Running main.exe..."
	./bin/main.exe
clean:
	echo "Removing .o/.exe..."
	rm -f *.o *.exe
# test:
# 	echo "Testing file"
# 	g++ -c src/extra/test.cpp src/helpers.cpp $(INCLUDE)
# 	g++ $(wildcard *.o) -o test.exe $(STANDARD) -Llib $(EXTERNAL)
# 	./test.exe 
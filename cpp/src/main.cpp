/*
Shynn's Clock - Displays Time in an analog format
Copyright (C) 2020  Shynn Lawrence

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/


#include <SFML/Graphics.hpp>
#include <iostream>
#include <math.h>
#define PI acos(-1.0)
#include <ctime>
#include <string>

int* getTime(){
    time_t ttime = time(0);
    int* z = (int*) malloc(3*sizeof(1));
    char* dt = ctime(&ttime);
    *(z  ) = (*(dt+11) - '0') * 10 + *(dt+12)-'0'; // Hours
    *(z+1) = (*(dt+14) - '0') * 10 + *(dt+15)-'0'; // Minutes
    *(z+2) = (*(dt+17) - '0') * 10 + *(dt+18) - '0'; // Seconds
    return z;
}

int main(){
    sf::ContextSettings settings;
    settings.antialiasingLevel = 10;
    sf::Clock clock;
    sf::RenderWindow window(sf::VideoMode(500, 500), "Clock", sf::Style::Titlebar | sf::Style::Close, settings);

    std::vector<sf::Vertex> hourVertices(2);
    std::vector<sf::Vertex> minuteVertices(2);
    std::vector<sf::Vertex> secondVertices(2);

    hourVertices.at(0) = sf::Vertex(sf::Vector2f(250, 250));
    minuteVertices.at(0) = sf::Vertex(sf::Vector2f(250, 250));
    secondVertices.at(0) = sf::Vertex(sf::Vector2f(250, 250));

    sf::CircleShape circle(230.f);
    circle.setPosition(sf::Vector2f(20, 20));
    circle.setPointCount(1000);
    circle.setFillColor(sf::Color::Transparent);
    circle.setOutlineColor(sf::Color::White);
    circle.setOutlineThickness(5);

    while(window.isOpen()){
        sf::Event event;
        if (window.pollEvent(event)){
            if (event.type == sf::Event::Closed){
                window.close();
            } else
            if (event.type == sf::Event::KeyPressed){
                std::cout << " Key Pressed " <<std::endl;
                window.clear();
            }
        }
        int* z = getTime();
        if (clock.getElapsedTime().asSeconds() > 0.1){
            window.clear();
            window.draw(&hourVertices[0], hourVertices.size(), sf::LineStrip);
            window.draw(&minuteVertices[0], minuteVertices.size(), sf::LineStrip);
            window.draw(&secondVertices[0], secondVertices.size(), sf::LineStrip);
            window.draw(circle);
            clock.restart();
            window.display();
        }
        int hour = *(z);
        int minute = *(z+1);
        int second = *(z+2);
        hourVertices.at(1) = sf::Vertex(sf::Vector2f(250 + 150*sin((30*hour)*PI/180), 250 - 150*cos((30*hour)*PI/180)));
        minuteVertices.at(1) = sf::Vertex(sf::Vector2f(250 + 230*sin((6*minute)*PI/180), 250 - 230*cos((6*minute)*PI/180)));
        secondVertices.at(1) = sf::Vertex(sf::Vector2f(250 + 200*sin((6*second)*PI/180), 250 - 200*cos((6*second)*PI/180)));
        
    }
    return 0;
}
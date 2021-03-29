import turtle
from time import sleep
from datetime import datetime
t = turtle.Turtle()
t.hideturtle()
ui = turtle.Turtle()
ui.speed(0)
ui.hideturtle()
ui.penup()
ui.goto(0, -200)
ui.pendown()
ui.circle(200)
t.penup()
h =turtle.Turtle()
h.hideturtle()
s = turtle.Turtle()
s.hideturtle()
h.showturtle()
t.pendown(); h.pendown(); s.pendown()
t.speed(0); h.speed(0); s.speed(0)
ui.up(); ui.goto(0, 0); ui.down()
def ttime(timput):
  return 90-timput
instmanager = {1:h, 2:t, 3:s, 4:ui}
def time():
    Hour = int(str(datetime.now())[11:13])%12
    Minute = int(str(datetime.now())[14:16])
    Second = int(str(datetime.now())[17:19])
    return Hour, Minute, Second
def SetHour(temp, inst):
    instmanager[inst].setheading(0)
    if inst != 4: instmanager[inst].clear()
    if not instmanager[inst].isvisible(): instmanager[inst].showturtle()
    if instmanager[inst].isdown() == False: instmanager[inst].penup()
    if inst == 4: instmanager[4].penup()
    #if inst == 4: print("Pen should be down", instmanager[inst].isdown())
    if inst == 1:
      temp = temp*30
      len1=100
      len2 = 100
      width = 10
    elif inst ==2:
      temp = temp*6
      len1, len2, width = 150, 150, 6
    elif inst == 3 or inst == 4:
      temp = temp*6
      len1, len2, width = 200, 250, 1
    instmanager[inst].setheading(ttime(temp))
    instmanager[inst].width(width)
    instmanager[inst].forward(len1)
    if inst == 3 or inst == 4:
      #if instmanager[inst].isdown() == False: instmanager[inst].penup()
      if inst == 4:
        instmanager[inst].pendown(); instmanager[inst].backward(50);
        instmanager[inst].penup(); instmanager[inst].forward(50)
      #instmanager[inst].write(str(int(temp/6)), font=("Arial", 14, "normal"))
      instmanager[inst].backward(len2)
    else:
      instmanager[inst].backward(len2)
    if inst == 3 or inst == 4:
      instmanager[inst].forward(50)

while True:
    hour, minute, second = time()
    SetHour(hour, 1)
    SetHour(minute, 2)
    SetHour(second, 3)
    SetHour(second, 4)

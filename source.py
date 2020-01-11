import ctypes
import time
import psutil
keyboardClicks = 0
mouseClicks = 0


def countClicks(countingTime):
    start = time.time()
    print("start")
    buttonState = []
    global mouseClicks
    global keyboardClicks
    numberOfKeys = 256
    for i in range(numberOfKeys):
        buttonState.append(0)
    maximum = 0
    while 1:
        if (time.time() - start) > countingTime:
            start = time.time()
            print("mouse clicks: " + str(mouseClicks))
            print("keyboard clicks: " + str(keyboardClicks))
            print("max: " + str(maximum))
            mouseClicks = 0
            keyboardClicks = 0
            # call sending
        for x in range(numberOfKeys):
            if ctypes.windll.user32.GetKeyState(x) not in [0, 1]:
                if buttonState[x] != 1:
                    if x > maximum:
                        maximum = x
                    if x < 5:
                        mouseClicks += 1
                        print(str(x) + " pressed (mouse)")

                    else:
                        keyboardClicks += 1
                        print(str(x) + " pressed (keyboard)")

                    buttonState[x] = 1
            else:
                if buttonState[x] != 0:
                    print(str(x) + " released")
                    buttonState[x] = 0
        time.sleep(0.001)


countClicks(60)

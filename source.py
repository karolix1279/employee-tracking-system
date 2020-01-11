import ctypes
import psutil
import time


def countClicks(countingTime, updatePeriod):
    start = time.time()
    print("start")
    buttonState = []
    mouseClicks = 0
    keyboardClicks = 0
    numberOfKeys = 256
    for i in range(numberOfKeys):
        buttonState.append(0)
    lastUpdate = time.time()
    numberOfUpdates = 0
    cpuUsage = 0
    memoryUsage = 0
    psutil.cpu_percent()
    while 1:
        if (time.time() - lastUpdate) > updatePeriod:
            cpuUsage += psutil.cpu_percent()
            memoryUsage += psutil.virtual_memory().percent
            lastUpdate = time.time()
            numberOfUpdates += 1
        if (time.time() - start) > countingTime:
            start = time.time()
            cpuUsage /= numberOfUpdates
            memoryUsage /= numberOfUpdates
            print("mouse clicks: " + str(mouseClicks))
            print("keyboard clicks: " + str(keyboardClicks))
            print("cpu usage: " + str(cpuUsage))
            print("memory usage: " + str(memoryUsage))
            # call sending
            mouseClicks = 0
            keyboardClicks = 0
            cpuUsage = 0
            memoryUsage = 0
        for x in range(numberOfKeys):
            if ctypes.windll.user32.GetKeyState(x) not in [0, 1]:
                if buttonState[x] != 1:
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
        time.sleep(0.01)


countClicks(10, 1)

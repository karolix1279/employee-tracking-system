import ctypes
import psutil
import time
import cv2
import json
import requests
import socket
server_address = "http://10.230.105.90:6789/api/collect"


def send(comp_name="default", keyboard_clicks=-1, mouse_clicks=-1, cpu_usage=-1, memory_usage=-1, image=None):
    data = {
        "computerName": "{}".format(comp_name),
        "countClickedButtonsKeyboard": keyboard_clicks,
        "countClickedButtonsMouse": mouse_clicks,
        "cpuUsage": cpu_usage,
        "memoryUsage": memory_usage,
        "image:": image.tolist()
    }
    data = json.dumps(data)
    requests.post(url=server_address, data=data.encode("utf-8"),
                  headers={'Content-type': 'application/json; charset=utf-8'})


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
            cam = cv2.VideoCapture(0)
            ret, image = cam.read()
            imageName = "image.png"
            cv2.imwrite(imageName, image)
            cam.release()
            computerName = socket.gethostname()
            send(comp_name=computerName, keyboard_clicks=keyboardClicks, mouse_clicks=mouseClicks, cpu_usage=cpuUsage,
                 memory_usage=memoryUsage, image=image)
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


countClicks(6, 1)

import ctypes
import psutil
import time
import cv2
import json
import requests
import socket
import getpass
from PIL import ImageChops
#from pyscreenshot import grab
import pywinusb.hid as hid
from requests.auth import HTTPBasicAuth
import os
from threading import Thread

server_address = "http://10.230.105.90:6789"


def get_ip():
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    try:
        s.connect(('10.255.255.255', 1))
        ip = s.getsockname()[0]
    except:
        ip = '127.0.0.1'
    finally:
        s.close()
    return ip


def send_json(comp_name="default", keyboard_clicks=-1, mouse_clicks=-1, cpu_usage=-1, memory_usage=-1, ip=None,
              current_user=None, is_screen_difference=0, is_devices_change=0):
    data = {
        "computerName": "{}".format(comp_name),
        "countClickedButtonsKeyboard": keyboard_clicks,
        "countClickedButtonsMouse": mouse_clicks,
        "cpuUsage": cpu_usage,
        "memoryUsage": memory_usage,
        "ip": ip,
        "currentUser": current_user,
        "screenChanges": is_screen_difference,
        "connectedDevicesChanges": is_devices_change
    }
    data = json.dumps(data)
    print(data)
    url = server_address + "/api/collect"
    requests.post(url=url, auth=HTTPBasicAuth(username='admin', password='admin1'), data=data.encode("utf-8"),
                  headers={'Content-type': 'application/json; charset=utf-8'})


def send_image(image, name="default"):
    url = server_address + "/api/image/%s" % name
    requests.post(url=url, auth=HTTPBasicAuth(username='admin', password='admin1'), files={'file': (open(image, 'rb'))})


def image_processing(computer_name="default", current_user="default", interval=300):
    start = time.time()
    while 1:
        if (time.time() - start) > interval:
            start = time.time()
            cam = cv2.VideoCapture(0)
            ret, image = cam.read()
            image_name = "image.png"
            cv2.imwrite(image_name, image)
            cam.release()
            send_image(image_name, name=(computer_name + "-" + current_user))
            os.remove(image_name)
            time.sleep(0.01)


def main(interval, update_period):
    new_thread = Thread(target=image_processing, args=(socket.gethostname(), getpass.getuser()))
    new_thread.start()
    start = time.time()
    print("start")
    button_state = []
    mouse_clicks = 0
    keyboard_clicks = 0
    number_of_keys = 256
    for i in range(number_of_keys):
        button_state.append(0)
    last_update = time.time()
    number_of_updates = 0
    cpu_usage = 0
    memory_usage = 0
#    im = grab()
    psutil.cpu_percent()
    all_hids = hid.find_all_hid_devices()
    total = 0
    for device in all_hids:
        total += 1

    while 1:
        if (time.time() - last_update) > update_period:
            cpu_usage += psutil.cpu_percent()
            memory_usage += psutil.virtual_memory().percent
            last_update = time.time()
            number_of_updates += 1
        if (time.time() - start) > interval:
            start = time.time()
            cpu_usage /= number_of_updates
            memory_usage /= number_of_updates
            print("mouse clicks: " + str(mouse_clicks))
            print("keyboard clicks: " + str(keyboard_clicks))
            print("cpu usage: " + str(cpu_usage))
            print("memory usage: " + str(memory_usage))
            computer_name = socket.gethostname()
            ip = get_ip()
            current_user = getpass.getuser()
#            diff = ImageChops.difference(grab(), im)
#            bbox = diff.getbbox()
#            if bbox is not None:
#                is_screen_difference = 1
#            else:
#                is_screen_difference = 0
            all_hids = hid.find_all_hid_devices()
            past_total = total
            total = 0
            for device in all_hids:
                total += 1
            if total == past_total:
                is_devices_change = 0
            else:
                is_devices_change = 1
            send_json(comp_name=computer_name, keyboard_clicks=keyboard_clicks, mouse_clicks=mouse_clicks,
                      cpu_usage=cpu_usage,
                      memory_usage=memory_usage, ip=ip, current_user=current_user,
#                      is_screen_difference=is_screen_difference,
                      is_devices_change=is_devices_change)

            mouse_clicks = 0
            keyboard_clicks = 0
            cpu_usage = 0
            memory_usage = 0
            number_of_updates = 0
        for x in range(number_of_keys):
            if ctypes.windll.user32.GetKeyState(x) not in [0, 1]:
                if button_state[x] != 1:
                    if x < 5:
                        mouse_clicks += 1
                        print(str(x) + " pressed (mouse)")
                    else:
                        keyboard_clicks += 1
                        print(str(x) + " pressed (keyboard)")

                    button_state[x] = 1
            else:
                if button_state[x] != 0:
                    print(str(x) + " released")
                    button_state[x] = 0
        time.sleep(0.01)


if __name__ == '__main__':
    main(6, 1)

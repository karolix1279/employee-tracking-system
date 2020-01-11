import json
import requests

server_address = "http://10.230.105.90:6789/api/collect"


def send(comp_name="default", keyboard_clicks=-1, mouse_clicks=-1):
    data = {
        "computerName": "{}".format(comp_name),
        "countClickedButtonsKeyboard": keyboard_clicks,
        "countClickedButtonsMouse": mouse_clicks
    }
    data = json.dumps(data)
    requests.post(url=server_address, json=data)


send()

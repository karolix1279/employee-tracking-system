import requests

server_address = "http://10.4.4.232:6789/api/collect"


def send(mc=None, kc=None, cpu_usage=None, ):
    # data = jsonify(mouse_clicks=mc, keyword_clicks=kc)
    requests.post(url=server_address)


send()

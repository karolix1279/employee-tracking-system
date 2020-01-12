Introduction
============

ETS (Employee Tracking System) is a tool which automates the process
of tracking employee during work from home. The metrics are displayed on Grafana dashboards.

Access
------

The Grafana dashboards may be found in the **General** folder.


Metrics documentation
=====================

Setup for
---------

### Client

Our client is written in python 3.x version. Main functionality of our client-app 
is scanning data from remote computer of employee, which is working from home, 
to detect if the user is at the computer. 

### Server

**Exposed metrics** are calculated for data collected from client-app.
We collect data about:

    - cpu usage 
    - ram memory usage
    - quantity of mouse and keyboard clicks
    - information if number of devices to ports changed
    - face recognition, to check that, if user is present in front of computer
    
Our server expose following metrics:

    - tracked_data_keyboard
    - human_face
    - tracked_data_devices_changes
    - tracked_data_memory
    - tracked_data_mouse
    - tracked_data_cpu


To face recognition we use external Azure API. If face is recognized, we set up
metric value to true.
All endpoints are secured with Basic Authentication.
For API description, look up Swagger endpoint **/swagger-ui.html**

### Prometheus/Grafana

To show your data we use Grafana, which is connected with Prometheus. 
Prometheus scrape metrics from server endpoint every 15 seconds, then Grafana select 
data with right query.

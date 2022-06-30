# satellite-downlink-coding-problem
Satellite Downlink Coding Problem

## Assumptions
1) Pass schedule csv is not blank
2) The comma separated values are not in double quotes
3) Assuming satellite pass start and end time will be perfect half an hour e.g. 00:00, 00:30, ... , 11:00, 11:30, 12:00, ... , 23:30, 00:00 

## How to run

```

git clone https://github.com/rdhadge/satellite-downlink.git

cd satellite-downlink

mvn clean install

java -jar target/satellite-downlink-1.0.0.jar --groud-station-bandwidth <ground-dtation-bandwidth> --satellite-pass-schedule <pass-schedule-file-path>

``` 

##### WY Space exercise

###### Intro

Your company has taken on a new project with WY Space and you have grabbed a user story (1701) related to ground station communications! Your task is to understand and implement a solution.

Document any assumptions you make as well as any thoughts on your solution.

###### User Story 1701

WY Space has a fleet of satellites, and a ground station to communicate with them. Each satellite has a downlink rate measured in units per 30 minutes. The ground station has a maximum bandwidth that it can handle at any one time; it can handle multiple satellites in parallel.

A satellite can only downlink data when the ground station can see it, this window is called a pass.

When the pass begins, the connection and downlink to the ground station is immediate, you may assume there are no delays. Similarly, the downlink will immediately stop when the pass ends. All passes are a minimum of 30 minutes.

WY Space would like to take a text based schedule (detailed below) and use a program that can find the 30 minute period where the total downlink (all satellite passes) will be at its maximum. Furthermore, they would like the program to determine if the ground station has the bandwidth to support this.

Since WY Space want to use your solution for multiple ground stations, the bandwidth of the ground station should be provided as an argument to the program.

###### Attached File: pass-schedule.txt

This is a text based schedule WY Space expect to use with your program.

The format is as follows:

Each line represents a single pass. A pass contains the satellite name, it’s bandwidth per 30 minute period, the start time of the pass, and the end time of the pass. The four elements of a pass are comma separated.

###### Notes to the candidate

Include a README text file detailing how we run your solution. The README can be in a format of your choice, e.g. plaintext, markdown, Word document etc.

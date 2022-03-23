# androidAppProject

<h2>About</h2>
Sheridan courses app is the app that each stuent at Sheridan college needed for managing their student life. This app give the students access to their course, academic calender & many other features. 

<h2>Features</h2>

1- Sign in activity : 
When the user clicks the sign-in button it should verify the sign-in by looking to the DB if the user is available or not. 
The shared preference is done already. 


2- Sign up activity : 
We have added the data validation. We have to save the user to the DB when he clicks to sign up and we. 


3- Event Fragment : 
We have to fetch the selected event from the Db that the user has chosen from the home Activity. We will add the quantity button where the user will choose how many tickets he needs and calculate the price and send it to the payment fragment when the user clicks the buy button.
We will be hardcoded 5 events to the DB. 



4- Payment Fragment : 
We have to validate the data that the user entered and if it validates we have to add the event booking to the DB 




5- BookingList Fragment :
Create an Ui as the attachment picture or any idea on your mind and when the fragment loaded we have to fetch all the event booking and the stay over that the user been done. 


6- Verified User :
Create the UI Write some information on how the user can be verified by attaching a photo f his id and we will give him access to the camera add a button this button gives the user to access the camera and take the photo and once when the user is done with that we will change the verified status for the user. 


7- <h3>About us fragment :</h3>
Just the Ui or anything you think it is useful



8- NearByEvent Fragment :
This fragment will be accessed from the Home Activity when the user clicks explore all. Where in this fragment we will show the user a map with markup for where exactly each of the 4 events happening. We have to create the Ui for this fragment I will be sending the picture. 

<img width="253" alt="Screen Shot 2020-11-15 at 01 05 22" src="https://user-images.githubusercontent.com/72468026/99178429-f7fcc600-26e0-11eb-94d9-0f96f3e24d5e.png">

9- Host Fragment :
We create UI similar to the guest fragment added these field to it :
var name :String = "" ,
var about :String = "",
var language : String = "",
var location :String ="",
var workExperience :String = ""


<h2>Initial Prototype</h2>
<img width="1213" alt="Screen Shot 2022-02-27 at 8 22 03 PM" src="https://user-images.githubusercontent.com/68449449/155909584-c9f71adb-5c7c-4edb-99fe-872dc2fa137d.png">


<br>
<h2>Navigation Prototype</h2>


<img width="1213" alt="Screen Shot 2022-02-27 at 8 21 55 PM" src="https://user-images.githubusercontent.com/68449449/155909592-843c3fb5-77f4-4e36-b39a-555346e4adb1.png">


<h2>Demo Video </h2>
<a href="https://youtu.be/SbnTSyzNlJo">Video</a> <br>

<h2>Links</h2>
<a href="https://github.com/dana1994123/SheridanCoursesApp.git">Github Repository</a> <br>
<a href="https://www.figma.com/file/N7Ax5OZHyuanLsAleBo4Pn/Sheridan-Course-App-V2?node-id=0%3A1">Figma Link</a> <br>
<a href="https://youtu.be/SbnTSyzNlJo">Video Demo</a> <br>

<img width="272" alt="Screen Shot 2020-11-15 at 01 04 56" src="https://user-images.githubusercontent.com/72468026/99178425-f3d0a880-26e0-11eb-92cf-0527792b0111.png">
<img width="274" alt="Screen Shot 2020-11-15 at 01 19 58" src="https://user-images.githubusercontent.com/72468026/99178441-0f3bb380-26e1-11eb-8b7d-ca7cd2908947.png">
<img width="273" alt="Screen Shot 2020-11-15 at 01 20 57" src="https://user-images.githubusercontent.com/72468026/99178445-1662c180-26e1-11eb-811c-93039305f608.png">
<img width="275" alt="Screen Shot 2020-11-15 at 01 10 11" src="https://user-images.githubusercontent.com/72468026/99178447-195db200-26e1-11eb-948d-14553aca0196.png">

StayOver fragment : 
We have to create the UI for it similar to the event fragment with these attribute : 
Hostname, description, 3 images, and date check box as in date fragment, visitor number limit,  and other information you feel is gonna be useful and don’t forget to add it in the stayOver class as well. 
We have to hardcoded 3 options for each culture, French, Italy, and India have the attribute that will be fetched based on the user choice in the Home Activity. 

We have to add the pay button that will navigate to the payment page and send the stayOver information along. 





We will be hardcoded 4 hosts to our app so please prepare these information on a word file and don’t hard codded in the Ui






Note: We have to add 3 languages to our app by default it gonna be English and we have to add dark mode and landscape for every layout


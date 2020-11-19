# androidAppProject


<img width="272" alt="Screen Shot 2020-11-15 at 01 04 56" src="https://user-images.githubusercontent.com/72468026/99178425-f3d0a880-26e0-11eb-92cf-0527792b0111.png">
<img width="274" alt="Screen Shot 2020-11-15 at 01 19 58" src="https://user-images.githubusercontent.com/72468026/99178441-0f3bb380-26e1-11eb-8b7d-ca7cd2908947.png">
<img width="273" alt="Screen Shot 2020-11-15 at 01 20 57" src="https://user-images.githubusercontent.com/72468026/99178445-1662c180-26e1-11eb-811c-93039305f608.png">
<img width="275" alt="Screen Shot 2020-11-15 at 01 10 11" src="https://user-images.githubusercontent.com/72468026/99178447-195db200-26e1-11eb-948d-14553aca0196.png">

StayOver fragment : 
We have to create the UI for it similar to the event fragment with these attribute : 
Hostname, description, 3 images, and date check box as in date fragment, visitor number limit,  and other information you feel is gonna be useful and don’t forget to add it in the stayOver class as well. 
We have to hardcoded 3 options for each culture, French, Italy, and India have the attribute that will be fetched based on the user choice in the Home Activity. 

We have to add the pay button that will navigate to the payment page and send the stayOver information along. 



Sign in activity : 

When the user clicks the sign-in button it should verify the sign-in by looking to the DB if the user is available or not. 
The shared preference is done already. 

<img width="272" src="https://user-images.githubusercontent.com/72468026/99178421-ec110400-26e0-11eb-81b3-32349b5d8cc6.png">

Sign up activity : 
We have added the data validation. We have to save the user to the DB when he clicks to sign up and we. 

<img width="272" src="https://user-images.githubusercontent.com/72468026/99178422-ef0bf480-26e0-11eb-9abc-717e57205f2f.png">

Event Fragment : 
We have to fetch the selected event from the Db that the user has chosen from the home Activity. We will add the quantity button where the user will choose how many tickets he needs and calculate the price and send it to the payment fragment when the user clicks the buy button.
We will be hardcoded 5 events to the DB. Each event should have these element: 
var name : String ="",
var location :String = "",
var duration :String = "" ,
var language :String = "",
var desc : String = "",
var price : Long = 0,
var icon1 : Image,
var icon2 : Image

<img width="279" alt="Screen Shot 2020-11-15 at 01 20 19" src="https://user-images.githubusercontent.com/72468026/99178436-0a76ff80-26e1-11eb-870e-d819ff97de28.png">



Payment Fragment : 
We have to validate the data that the user entered and if it validates we have to add the event booking to the DB 

<img width="274" alt="Screen Shot 2020-11-15 at 01 20 44" src="https://user-images.githubusercontent.com/72468026/99178443-1498fe00-26e1-11eb-8148-546c68af8b53.png">


BookingList Fragment :
Create an Ui as the attachment picture or any idea on your mind and when the fragment loaded we have to fetch all the event booking and the stay over that the user been done. 


Verified User :
Create the UI Write some information on how the user can be verified by attaching a photo f his id and we will give him access to the camera add a button this button gives the user to access the camera and take the photo and once when the user is done with that we will change the verified status for the user. 


About us fragment :
Just the Ui or anything you think it is useful

<img width="270" alt="Screen Shot 2020-11-15 at 01 05 50" src="https://user-images.githubusercontent.com/72468026/99178449-1bc00c00-26e1-11eb-92bf-24bf8be00600.png">

NearByEvent Fragment :
This fragment will be accessed from the Home Activity when the user clicks explore all. Where in this fragment we will show the user a map with markup for where exactly each of the 4 events happening. We have to create the Ui for this fragment I will be sending the picture. 

<img width="253" alt="Screen Shot 2020-11-15 at 01 05 22" src="https://user-images.githubusercontent.com/72468026/99178429-f7fcc600-26e0-11eb-94d9-0f96f3e24d5e.png">

Host Fragment :
We create UI similar to the guest fragment added these field to it :
var name :String = "" ,
var about :String = "",
var language : String = "",
var location :String ="",
var workExperience :String = ""

We will be hardcoded 4 hosts to our app so please prepare these information on a word file and don’t hard codded in the Ui






Note: We have to add 3 languages to our app by default it gonna be English and we have to add dark mode and landscape for every layout


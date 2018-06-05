## The lack of diversity in STEM fields is solvable
**Code2College** (https://www.code2college.org) is an Austin-based nonprofit that is dramatically increasing the number of minority and low-income high school students who enter and excel in STEM undergraduate majors and careers. In our program model, we source dozens of volunteers from companies like Google, General Motors and RetailMeNot to deliver 100 hours of coding education and professional skills development to hundreds of Central Texas students each year. By providing a comprehensive mix of technical education, STEM industry exposure, mentorship and paid, technical experience - _ the top 20% of Code2College students participated in paid, coding internships _ - we are making an impact on the lack of diversity seen across STEM fields. 

Our organization exists because an overwhelming majority of diverse students who select a STEM major, ultimately drop those majors by switching to a liberal arts degree or dropping out of college completely. We believe that our programming is changing outcomes for these students, but also realize that **"what gets measured, gets improved"** and if we want to improve these outcomes, we need to start understanding the challenges that our students are facing, and at the **student-level**. This is what the Code2College _ Risk Mitigation Tool (RMT) _ is all about. 

## STEM attrition risk-scoring
The Code2College RMT assigns risk scores (A-F) to program participants related to a variety of adverse events including undergraduate program attrition and STEM major switch. These risk scores update dynamically based on student responses to a variety of questions that roll up into these events/outcomes including "have you missed more than 5 days of school this semester?" and "do you have an adult at home who is always able to help?" This tool will allow us to proactively provide support to our students based on where they score when we initially engage with them, and reactively to provide ongoing support based on life changes while in and after the program.

## RMT Design & Development ("How we built it")
In order to develop our RMT, we first created an algorithm in Java that determined the risk factors associated with students who completed a survey. We later implemented this algorithm using NodeJS and JavaScript to create a website which displayed the data we collected. Additionally we heavily utilized API's as well as Bootstrap in order to create a user-friendly interface, that can live update new survey data.

## Challenges we ran into
Our main challenge was initially developing an accurate algorithm in Java, which could predict the likelihood of students dropping out of or switching majors in college. We additionally faced challenges managing data from a google spreadsheet in an effective manner.
 
## Accomplishments that we're proud of
We are proud that we successfully developed our MVP, we were able to develop an algorithm which was able to reflect data with certain accuracy. Additionally we are proud that we were able to create a website which can live update based on changes made to the data, as well as the extensive statistical analysis and research we conducted on it.
Our code is available on [GitHub](https://github.com/AbhijayS/C2C)

![Risk Mitigation Tool](https://challengepost-s3-challengepost.netdna-ssl.com/photos/production/software_photos/000/650/717/datas/gallery.jpg)
![Number of Mentors Predictions](https://challengepost-s3-challengepost.netdna-ssl.com/photos/production/software_photos/000/650/718/datas/gallery.jpg)

## What we learned
We learned how to fetch data from a google form and displaying the survey data in an intuitive format on the website using back-end technologies like NodeJs and several APIs. We learned about front-end technologies like bootstrap and displaying data in a clean format. We developed better coding habits for cleaning data from surveys.

## What's next for Code2College Risk Mitigation Tool (RMT)
Code2College will start serving at least 1,000 Central Texas High School students annually in Fall 2019 and have over 2,000 program alumni in Fall 2021. By next year, we'll hit a tipping point at which manual risk scoring based on empirical data can shift to dynamic risk scoring based on machine learning and the tens of thousands of data that we'll receive monthly from our students. Further, Code2College will use those data to improve and inform student-level support from communication to technical resource sharing. 

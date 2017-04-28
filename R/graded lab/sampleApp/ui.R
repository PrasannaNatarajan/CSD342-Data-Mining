library(shiny)
library(leaflet)
# Define UI for application that draws a map and a donut chart
shinyUI(fluidPage(
  
  # Application title
  titlePanel("Airquality of USA"),
  
  # Sidebar with a slider input for the number of bins
  sidebarLayout(
    sidebarPanel(
      
      selectInput("years", label = h3("Select a Year"), 
                  choices = list("2000" ="C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\2000.csv", 
                                 "2004" = "C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\2004.csv",
                                 "2008" = "C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\2008.csv", 
                                 "2012" = "C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\2012.csv",
                                 "2016" = "C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\clean_pollution.csv"), 
                  selected = "C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\clean_pollution.csv"),
      
      selectInput("state", label=h3("Select a State"), choices = list ("Alabama" = 1, "Alaska" = 2, "Arizona" = 4, "Arkansas" = 5, "California" = 6, "Colorado" = 8, "Connecticut" = 9, "Delaware" = 10, "District Of Columbia" = 11, "Florida" = 12, "Georgia" = 13, "Hawaii" = 15, "Idaho" = 16, "Illinois" = 17, "Indiana" = 18, "Iowa" = 19, "Kansas" = 20, "Kentucky" = 21, "Louisiana" = 22, "Maine" = 23, "Maryland" = 24, "Massachusetts" = 25, "Michigan" = 26, "Minnesota" = 27, "Mississippi" = 28, "Missouri" = 29, "Montana" = 30, "Nebraska" = 31, "Nevada" = 32, "New Hampshire" = 33, "New Jersey" = 34, "New Mexico" = 35, "New York" = 36, "North Carolina" = 37, "North Dakota" = 38, "Ohio" = 39, "Oklahoma" = 40, "Oregon" = 41, "Pennsylvania" = 42, "Rhode Island" = 44, "South Carolina" = 45, "South Dakota" = 46, "Tennessee" = 47, "Texas" = 48, "Utah" = 49, "Vermont" = 50, "Virginia" = 51, "Washington" = 53, "West Virginia" = 54, "Wisconsin" = 55, "Wyoming" = 56, "Puerto Rico" = 72, "Virgin Islands" = 78, "Country Of Mexico" = 80), selected=1),
      
      selectInput("parameters", label = h3("Select a Pollutant"), 
                  choices = list("Ozone" =44201, "PM2.5" = 88101,
                                 "CO" = 42101,"SO2" = 42401, "Lead" = 12128), 
                  selected = 0)
    ),
    
    # Show a plot of the generated distribution
    mainPanel(
      leafletOutput("map"),
      plotlyOutput("donut")
    )
  )
))

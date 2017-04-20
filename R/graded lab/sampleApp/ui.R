library(shiny)
library(leaflet)
# Define UI for application that draws a histogram
shinyUI(fluidPage(
  
  # Application title
  titlePanel("Lab3"),
  
  # Sidebar with a slider input for the number of bins
  sidebarLayout(
    sidebarPanel(
      
      selectInput("years", label = h3("Select a Year"), 
                  choices = list("1990" ="C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\1990.csv", 
                                 "2000" = "C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\2000.csv",
                                 "2005" = "C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\2005.csv", 
                                 "2010" = "C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\2010.csv",
                                 "2016" = "C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\clean_pollution.csv"), 
                  selected = "C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\clean_pollution.csv"),
      
      selectInput("state", label = h3("Select a State"), 
                  choices = list("State 1" = 1, "State 2" = 2,
                                 "State 3" = 4), selected = 1),
      
      selectInput("parameters", label = h3("Select a Parameter"), 
                  choices = list("Ozone" =44201, "PM2.5" = 88101,
                                 "CO" = 42101), selected = 0)
      ),
    
    # Show a plot of the generated distribution
    mainPanel(
      leafletOutput("map"),
      plotlyOutput("donut")
    )
  )
))

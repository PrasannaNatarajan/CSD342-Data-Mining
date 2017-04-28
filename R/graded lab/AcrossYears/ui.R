shinyUI(fluidPage(
  
  # Application title
  titlePanel("Comparison over years"),
  
  # Sidebar with a slider input for the number of bins
  sidebarLayout(
    sidebarPanel(
      selectInput("parameters", label = h3("Select a Pollutant"), 
                  choices = list("Ozone" =44201, "PM2.5" = 88101,
                                 "CO" = 42101,"SO2" = 42401, "Lead" = 12128), 
                  selected = 44201)
    ),
    
    # Show a plot of the generated distribution
    mainPanel(
      plotlyOutput("barPlot")
    )
  )
))

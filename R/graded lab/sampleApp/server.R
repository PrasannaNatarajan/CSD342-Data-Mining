library(shiny)
library(maps)
library(sp)
library(rgeos)
library(rgdal)
library(maptools)
library(dplyr)
library(leaflet)
library(scales)

shinyServer(function(input, output) {
  
  output$map <-renderLeaflet({
    makeMap(input$years,input$parameters)
  })
  output$donut <-renderPlotly({
    source('~/Studies/Semester 6/Data mining/labs/lab1/CSD342-Data-Mining/R/graded lab/makeDonut.R')
    makeDonut(input$years,input$state,input$parameters)
  })
})

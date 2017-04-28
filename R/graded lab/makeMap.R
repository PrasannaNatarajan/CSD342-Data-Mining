library(maps)
library(sp)
library(rgeos)
library(rgdal)
library(maptools)
library(dplyr)
library(leaflet)
library(scales)

makeMap <- function(years,input_para){
  print(years)
  if(years == "C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\clean_pollution.csv"){
    if(exists("df2016")){
      df <- df2016
      print("here 2016")
    }
  }
  else if(years == "C:\\Users\\prasanna\\Documents\\Studies\\Semester 6\\Data mining\\labs\\lab1\\CSD342-Data-Mining\\R\\graded lab\\2000.csv"){
    if(exists("df2000")){
      df <- df2000
      print("here 2012")
    }
  }
  else{
    df <- read.csv(years)
    print("inside else")
  }
  
  
  state_dat <- statePreprocess(df,input_para)
  
  colnames(state_dat) <- c("GEOID", "airqlty")
  
  state_dat$GEOID <- formatC(state_dat$GEOID, width = 2, format = "d", flag = "0")
  if(!exists("us.map")){
    us.map <- readOGR(dsn = "C:/Users/prasanna/Documents/Studies/Semester 6/Data mining/labs/lab1/CSD342-Data-Mining/R/graded lab/tl_2016_us_state", layer = "tl_2016_us_state", stringsAsFactors = FALSE)  
    print("here us.map")  
  }
  
  
  leafmap <- merge(us.map, state_dat, by=c("GEOID"))
  
  popup_dat <- paste0("<strong>state: </strong>", 
                      leafmap$NAME, 
                      "<br><strong>Value: </strong>", 
                      leafmap$airqlty)
  
  pal <- colorQuantile("YlOrRd", NULL, n = 9)
  
  leaflet(data = leafmap) %>%setView(lng = -98.583, lat = 39.833, zoom = 3)%>%
    addTiles() %>%
    addPolygons(fillColor = ~pal(airqlty), 
                fillOpacity = 0.8, 
                color = "#BDBDC3", 
                weight = 1,
                popup = popup_dat) %>%
    addLegend(pal = pal, values = leafmap$airqlty, opacity = 1.0)
  
}
library(sp)
library(rgeos)
library(rgdal)
library(maptools)
library(dplyr)
library(leaflet)
library(scales)

state_dat <- read.csv("42101  state.csv")
colnames(state_dat) <- c("GEOID", "airqlty")
state_dat$GEOID <- formatC(state_dat$GEOID, width = 2, format = "d", flag = "0")
us.map <- readOGR(dsn = "C:/Users/prasanna/Documents/Studies/Semester 6/Data mining/labs/lab1/CSD342-Data-Mining/R/graded lab/tl_2016_us_state", layer = "tl_2016_us_state", stringsAsFactors = FALSE)
leafmap <- merge(us.map, state_dat, by=c("GEOID"))
popup_dat <- paste0("<strong>state: </strong>", 
                    leafmap$NAME, 
                    "<br><strong>Value: </strong>", 
                    leafmap$airqlty)
pal <- colorQuantile("YlOrRd", NULL, n = 20)
leaflet(data = leafmap) %>% addTiles() %>%
  addPolygons(fillColor = ~pal(airqlty), 
              fillOpacity = 0.8, 
              color = "#BDBDC3", 
              weight = 1,
              popup = popup_dat)
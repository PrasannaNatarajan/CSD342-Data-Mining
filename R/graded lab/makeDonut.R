makeDonut <- function(Year,State_Code,Parameter_Code){
  library("plotly")
  library(RColorBrewer)
  data <- read.csv(Year)
  ##print(State_Code)
  ##print(data$State.Code)
  data_subs <- subset(data,(State.Code == State_Code),select = c("County.Name","Arithmetic.Mean","Parameter.Code"))
  s<- paste("County level pollution overall")
  if(Parameter_Code != 0){
    data_subs <- subset(data_subs,Parameter.Code == Parameter_Code,select = c("County.Name","Arithmetic.Mean"))  
    if(Parameter_Code == 44201){
      s <- paste("County level pollution of","Ozone")
    }
    else if(Parameter_Code == 88101){
      s <- paste("County level pollution of","PM2.5")
    }
    else if(Parameter_Code == 42101){
      s <- paste("County level pollution of","CO")
    }
    else if(Parameter_Code == 12128){
      s <- paste("County level pollution of","Lead")
    }else if(Parameter_Code == 42401){
      s <- paste("County level pollution of","SO2")
    }else {
      s <- paste("County level pollution of",Parameter_Code)
    }
    print("here")
  }

  u <- print(unique(data_subs$County.Name))
  print("length of u =")
  print(length(u))
  m<-0;
  for(i in 1:length(u)){m<-c(m,mean(data_subs[data_subs$County.Name==u[i],2]))}
  print("length of v =")
  print(length(m))
  m<-m[2:length(m)]
  newdf <- data.frame(u,m)
  p <- newdf %>% 
    plot_ly(labels = ~u,values = ~m,marker = list(color = colorRampPalette(brewer.pal(11,"Spectral"))(100) )) %>% 
    add_pie(hole=0.6) %>% 
    layout(title = s,showlegend=F,
           xaxis = list(showgrid = FALSE, zeroline = FALSE,showticklabels = FALSE),
           yaxis = list(showgrid = FALSE, zeroline = FALSE,showticklabels = FALSE))
  p
}
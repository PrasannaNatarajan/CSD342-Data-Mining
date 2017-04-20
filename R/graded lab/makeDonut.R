makeDonut <- function(State_Code,Parameter_Code){
  library("plotly")
  data <- read.csv("~/Studies/Semester 6/Data mining/labs/lab1/CSD342-Data-Mining/R/graded lab/clean_pollution.csv")
  data_subs <- subset(data,State.Code == State_Code,select = c("County.Name","Arithmetic.Mean","Parameter.Code"))
  s<- paste("County level pollution overall")
  if(Parameter_Code != 0){
    data_subs <- subset(data_subs,Parameter.Code == Parameter_Code,select = c("County.Name","Arithmetic.Mean"))  
    s <- paste("County level pollution of",Parameter_Code)
    print("here")
  }
  
  u <- unique(data_subs$County.Name)
  m<-0;
  for(i in 1:length(u)){m<-c(m,mean(data_subs[data_subs$County.Name==u[i],2]))}
  m<-m[2:length(m)]
  newdf <- data.frame(u,m)
  p <- newdf %>% 
    plot_ly(labels = ~u,values = ~m) %>% 
    add_pie(hole=0.6) %>% 
    layout(title = s,showlegend=F,
           xaxis = list(showgrid = FALSE, zeroline = FALSE,showticklbels = FALSE),
           yaxis = list(showgrid = FALSE, zeroline = FALSE,showticklbels = FALSE))
  p
}
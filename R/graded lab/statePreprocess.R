statePreprocess <- function(tempdf,para_code){
  tempdf_subs <- subset(tempdf,Parameter.Code==para_code,select = c("State.Name","Arithmetic.Mean","State.Code"))
  ##s<-paste(toString(para_code)," state.csv")
  m<-0
  for (i in 1:length(unique(tempdf_subs$State.Name))){
    m<-c(m,mean(tempdf_subs[tempdf_subs$State.Name==unique(tempdf_subs$State.Name)[i],2]))
  }
  newdf <- data.frame(unique(tempdf_subs$State.Code),m[2:(length(unique(tempdf_subs$State.Name))+1)])
  newdf
  ##write.csv(newdf,s,row.names = FALSE)
}
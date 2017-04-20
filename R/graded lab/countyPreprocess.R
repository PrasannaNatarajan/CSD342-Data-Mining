countyPreprocess <- function(tempdf,para_code){
  tempdf_subs <- subset(tempdf,Parameter.Code==para_code,select = c("County.Name","Arithmetic.Mean","County.Code"))
  s<-paste(toString(para_code),"county.csv")
  m<-0
  uniCountyNames <- unique(tempdf_subs$County.Name)
  print(length(uniCountyNames))
  print(length(unique(tempdf_subs$County.Code)))
  size <- 0
  for (i in 1:(length(uniCountyNames)+1)){
    m<-c(m,mean(tempdf_subs[tempdf_subs$County.Name==uniCountyNames[i],2]))
    ##print(i)
    ##print(size)
    size<-size+1
  }
  newdf <- data.frame(unique(tempdf_subs$County.Code),m[2:(length(uniCountyNames)+1)])
  write.csv(newdf,s,row.names = FALSE)
}
preProcess <- function(){
  
  # pm2.5local 88101
  # ozone 44201
  # co 42101
  # so2 42401
  # pb 12128
  
  pc <- c(88101, 44201, 42101, 42401, 12128); #parameter codes
  yr <- c(2000,2004,2008,2012,2016) #years
  mtc <- c("Arithmetic.Mean", "Arithmetic.Standard.Dev")
  
  for(i in 1:5) #loop over years
  {
    year <- yr[i]
    
    for(j in 1:5) #loop over parameter codes
    { 
      para_code <- pc[j]
      file <- paste("annual_all_",toString(year),".csv",sep="")
      tempdf = read.csv(file);
      
      for(k in 1:2) #loop over data metric types
      {
        metric <- mtc[k]    
          
        ######## state files ###########
        tempdf_subs <- subset(tempdf,Parameter.Code==para_code,select = c("State.Name",metric))
        folder <- paste("state_",toString(year),sep="")
        s<-paste(folder,"_",toString(para_code),"_",metric,".csv",sep="")
        m<-0
        for (i in 1:50)
        {
          m<-c(m,mean(tempdf_subs[tempdf_subs$State.Name==state.name[i],2]))
        }
        newdf <- data.frame(state.name,m[2:51])
        write.csv(newdf,s,row.names = FALSE)
        
        
        ###### county files #########
        tempdf_subs <- subset(tempdf,Parameter.Code==para_code,select = c("County.Name",metric))
        folder <- paste("county_",toString(year),sep="")
        s<-paste(folder,"_",toString(para_code),"_",metric,".csv",sep="")
        m<-0
        uniCountyNames <- unique(tempdf_subs$County.Name)
        size <- 0
        for (i in 1:(length(uniCountyNames)+1))
        {
          m<-c(m,mean(tempdf_subs[tempdf_subs$County.Name==uniCountyNames[i],2]))
          size<-size+1
        }
        newdf <- data.frame(uniCountyNames,m[2:(length(uniCountyNames)+1)])
        write.csv(newdf,s,row.names = FALSE)
      }
    }
  }
}


preProcess()

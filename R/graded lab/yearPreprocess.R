YearPreprocess <- function(para_code){
  
  #reading all the csv's into dataframes
  if(!exists("df2016")){
    df2016 <- read.csv("2016.csv")
  }
  if(!exists("df2000")){
    df2000 <- read.csv("2000.csv")
  }
  if(!exists("df2004")){
    df2004 <- read.csv("2004.csv")
  }
  if(!exists("df2008")){
    df2008 <- read.csv("2008.csv")
  }
  if(!exists("df2012")){
    df2012 <- read.csv("2012.csv")
  }
  
  if(!exists("df_all")){
    foo <- list(df2016,df2000,df2004,df2008,df2012)
    df_all <- do.call("rbind",foo)
  }
  
  #print(df_all$Parameter.Code)
  #print("here");
  #print(para_code)
  df_sub <- subset(df_all,Parameter.Code==para_code,select = c("State.Name","State.Code","Arithmetic.Mean","Year"))
  m_2016<-0
  m_2000<-0
  m_2004<-0
  m_2008<-0
  m_2012<-0
    
  for (i in 1:(length(unique(df_sub$State.Name))-1)){
      m_2016<-c(m_2016,mean(df_sub[(df_sub$State.Name==unique(df_sub$State.Name)[i])&(df_sub$Year == 2016),3]));
  }
    
  for (i in 1:(length(unique(df_sub$State.Name))-1)){
    m_2000<-c(m_2000,mean(df_sub[(df_sub$State.Name==unique(df_sub$State.Name)[i])&(df_sub$Year == 2000),3]));
  }
  
  for (i in 1:(length(unique(df_sub$State.Name))-1)){
    m_2004<-c(m_2004,mean(df_sub[(df_sub$State.Name==unique(df_sub$State.Name)[i])&(df_sub$Year == 2004),3]));
  }
  
  for (i in 1:(length(unique(df_sub$State.Name))-1)){
    m_2008<-c(m_2008,mean(df_sub[(df_sub$State.Name==unique(df_sub$State.Name)[i])&(df_sub$Year == 2008),3]));
  }
  
  for (i in 1:(length(unique(df_sub$State.Name))-1)){
    m_2012<-c(m_2012,mean(df_sub[(df_sub$State.Name==unique(df_sub$State.Name)[i])&(df_sub$Year == 2012),3]));
  }
  #print(length(m_2016))
  #print(length(m_2012))
  #print(dim(m_2008))
  #print(length(m_2004))
  #print(length(m_2000))
  #print(length(unique(df_sub$State.Code)))
  #print(length(m_2016[2:(length(unique(df_sub$State.Name)))]))
  #print(length(m_2000[2:(length(unique(df_sub$State.Name)))]))
  #print(length(m_2004[2:(length(unique(df_sub$State.Name)))]))
  #print(length(m_2008[2:(length(unique(df_sub$State.Name)))]))
  #print(length(m_2012[2:(length(unique(df_sub$State.Name)))]))
  st <- unique(df_sub$State.Code)[1:length(unique(df_sub$State.Code))-1]
  #print(length(st))
  st_name <- unique(df_sub$State.Name)[1:length(unique(df_sub$State.Name))-1]
  #print(length(st_name))
  newdf <- data.frame(st_name,m_2016[2:(length(st_name)+1)],m_2000[2:(length(st_name)+1)],m_2004[2:(length(st_name)+1)],m_2008[2:(length(st_name)+1)],m_2012[2:(length(st_name)+1)])
  colnames(newdf)<-c("State.Code","avg_2016","avg_2000","avg_2004","avg_2008","avg_2012")
  ##newdf <- subset(newdf,!is.nan(newdf[[2]]))
  newdf
 
}
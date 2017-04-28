library(plotly)
shinyServer(function(input, output) {
  
  output$barPlot <-renderPlotly({
    source('~/Studies/Semester 6/Data mining/labs/lab1/CSD342-Data-Mining/R/graded lab/yearPreprocess.R')
    temp <- YearPreprocess(input$parameters)
    p <- plot_ly(temp, x = ~State.Code, y = ~avg_2000, type = 'bar', name = '2000',marker = list(color = 'rgb(255,165,0)')) %>%
      add_trace(y = ~avg_2004, name = '2004',marker = list(color = 'rgb(20,185,204)')) %>% 
      add_trace(y=~avg_2008,name='2008',marker = list(color = 'rgb(0,255,139)')) %>% 
      add_trace(y = ~avg_2012,name='2012',marker = list(color = 'rgb(133,39,178)')) %>% 
      add_trace(y=~avg_2016,name='2016',marker = list(color = 'rgb(71,95,255)')) %>%
      layout(yaxis = list(title = 'Mean'),xaxis = list(title=''), barmode = 'group')
    p
  })
})

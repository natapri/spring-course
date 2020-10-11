let chartDataStr = decodeHtml(chartData);
let chartJsonArray = JSON.parse(chartDataStr);

let arrayLength = chartJsonArray.length;
let numericData = [];
let labelData = [];

for (let i=0; i<arrayLength; i++){
    numericData[i]=chartJsonArray[i].value;
    labelData[i]=chartJsonArray[i].label;
}


// For a pie chart
 new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
     // The data for our dataset
        data: {
            labels: labelData,
            datasets: [{
                label: 'My First dataset',
                backgroundColor:["#3e95cd", "#8e5ea2", "#3cba9f"],
                data: numericData
            }]
        },

        // Configuration options go here
        options: {
          title: {
             display: true,
             text: "Project status"
          }
        }
});

function decodeHtml(html){
     let text=document.createElement("textarea");
     text.innerHTML=html;
     return text.value;
}
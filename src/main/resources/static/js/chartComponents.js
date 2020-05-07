function renderExpenses(expenses, element) {
    let expenseData = buildExpenseData(expenses);
    var ctx = element.getContext('2d');
    var chart = new Chart(ctx, {
        responsive: true,
        // The type of chart we want to create
        type: 'line',
        // The data for our dataset
        data: {
            labels: expenseData.dates,
            datasets: [{
                fill: false,
                label: 'Despesas',
                backgroundColor: '#0078ff',
                borderColor: '#0078ff',
                data: expenseData.values,
                lineTension:0
            }]
        },

        // Configuration options go here
        options: {
            scales:{
                yAxes:[
                    {
                        ticks:{
                            callback: function(value, index, values){
                                return "R$ " + value;
                            }
                        }
                    }
                ]
            }
        }
    });
}

function buildExpenseData(expenses){
    let expenseData = {
        values: [],
        dates: []
    }
    expenses.sort((a, b) => new Date(a.date) - new Date(b.date))
    expenses.forEach(expense => {
        expenseData.values.push(expense.value);
        expenseData.dates.push(new Date(expense.date).toLocaleDateString())
    });
    return expenseData;
}
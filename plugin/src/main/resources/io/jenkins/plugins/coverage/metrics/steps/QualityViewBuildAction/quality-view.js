const chart = echarts.init(document.getElementById('quality-chart'));
const data = window.metricsData;

const option = {
    tooltip: { trigger: 'axis' },
    grid: { left: 40, right: 20, bottom: 40, top: 40, containLabel: true },
    xAxis: {
        type: 'category',
        data: data.metrics.map(m => m.metric),
        axisLabel: { rotate: 15 }
    },
    yAxis: { type: 'value', name: 'Value' },
    series: [{
        name: 'Metrics',
        type: 'bar',
        data: data.metrics.map(m => m.value),
        itemStyle: {
            color: function (params) {
                const name = params.name;
                if (name.includes('Branch')) return '#f28e2b';
                if (name.includes('Line C')) return '#4e79a7';
                return '#59a14f';
            }
        },
        label: {
            show: true,
            position: 'top',
            formatter: function (p) {
                const label = p.name;
                return label.includes('Coverage')
                    ? p.value.toFixed(1) + '%'
                    : p.value;
            }
        }
    }]
};

chart.setOption(option);
window.addEventListener('resize', () => chart.resize());

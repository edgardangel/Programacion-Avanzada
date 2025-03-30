using System.Reflection;
using WeatherClient.Models;

namespace WeatherClient;

public partial class MainPage : ContentPage
{
    public MainPage()
    {
        InitializeComponent();
    }

    private async void btnRefresh_Clicked(object sender, EventArgs e)
    {
        // Desactivar el botón y mostrar el indicador de carga
        btnRefresh.IsEnabled = false;
        actIsBusy.IsRunning = true;

        // Recuperar los datos del clima
        var weatherData = await Services.WeatherServer.GetWeather(txtPostalCode.Text);

        // Actualizar los controles con los datos del clima
        lblWind.Text = weatherData.Wind.ToString("0.0");  // Formato de viento
        lblHumidity.Text = weatherData.Humidity.ToString("0.0");  // Formato de humedad
        lblPrecipitation.Text = weatherData.Precipitation.ToString("0.0");  // Formato de precipitación
        lblTemperature.Text = weatherData.Temperature.ToString("0.0");  // Formato de temperatura

        // Establecer la imagen según el tipo de clima
        imgCondition.Source = weatherData.Condition switch
        {
            WeatherType.Sunny => ImageSource.FromFile("sunny.png"),
            WeatherType.Cloudy => ImageSource.FromFile("cloud.png"),
            _ => ImageSource.FromFile("question.png")
        };

        // Rehabilitar el botón y desactivar el indicador de carga
        btnRefresh.IsEnabled = true;
        actIsBusy.IsRunning = false;
    }
}

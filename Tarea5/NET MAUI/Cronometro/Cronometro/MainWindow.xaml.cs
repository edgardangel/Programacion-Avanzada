using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Threading;

namespace Cronometro
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private DateTime tiempoInicio;
        private DateTime tiempoDetener;
        private DispatcherTimer timer;

        public MainWindow()
        {
            InitializeComponent();

            // Inicializar el temporizador
            timer = new DispatcherTimer();
            timer.Interval = TimeSpan.FromMilliseconds(100); // Intervalo de 100 ms
            timer.Tick += Timer_Tick;
        }

        // Acción al presionar el botón "Iniciar"
        private void BotonIniciar_Click(object sender, RoutedEventArgs e)
        {
            tiempoInicio = DateTime.Now;
            campoInicio.Text = FormatearTiempo(tiempoInicio);
            campoDetener.Clear();
            campoSalida.Clear();
            timer.Start();
        }

        // Acción al presionar el botón "Detener"
        private void BotonDetener_Click(object sender, RoutedEventArgs e)
        {
            tiempoDetener = DateTime.Now;
            campoDetener.Text = FormatearTiempo(tiempoDetener);

            // Calcular el tiempo transcurrido
            double tiempoTranscurrido = (tiempoDetener - tiempoInicio).TotalSeconds;
            campoSalida.Text = tiempoTranscurrido.ToString("F2");

            timer.Stop();
        }

        // Acción al presionar el botón "Salir"
        private void BotonSalir_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        // Método para formatear el tiempo en formato hh:mm:ss.SSS
        private string FormatearTiempo(DateTime tiempo)
        {
            return tiempo.ToString("HH:mm:ss.fff");
        }

        // Actualización del cronómetro en tiempo real (solo si el cronómetro está en marcha)
        private void Timer_Tick(object sender, EventArgs e)
        {
            if (timer.IsEnabled)
            {
                var tiempoActual = DateTime.Now;
                campoInicio.Text = FormatearTiempo(tiempoActual);
            }
        }
    }
}
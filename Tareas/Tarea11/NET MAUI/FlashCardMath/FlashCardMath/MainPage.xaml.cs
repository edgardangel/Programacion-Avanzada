using Microsoft.Maui.Controls;
using System;
using System.Linq;
using System.Timers;

namespace FlashCardMath
{
    public partial class MainPage : ContentPage
    {
        private Random random;
        private int num1, num2, respuesta, intentos = 0, correctos = 0;
        private Timer temporizador;
        private int contadorTiempo = 0;

        public MainPage()
        {
            InitializeComponent();
            random = new Random();

            // Crear el temporizador
            temporizador = new Timer(1000);
            temporizador.Elapsed += Temporizador_Elapsed;
        }

        private void BtnIniciar_Clicked(object sender, EventArgs e)
        {
            num1 = random.Next(1, 11);
            num2 = random.Next(1, 11);
            txtNumeros.Text = $"{num1} , {num2}";

            // Seleccionar la operación
            if (chkSuma.IsChecked)
            {
                lblPregunta.Text = $"{num1} + {num2} = ?";
                respuesta = num1 + num2;
            }
            else if (chkResta.IsChecked)
            {
                lblPregunta.Text = $"{num1} - {num2} = ?";
                respuesta = num1 - num2;
            }
            else if (chkMultiplicacion.IsChecked)
            {
                lblPregunta.Text = $"{num1} * {num2} = ?";
                respuesta = num1 * num2;
            }
            else if (chkDivision.IsChecked)
            {
                lblPregunta.Text = $"{num1} / {num2} = ?";
                respuesta = num1 / num2;
            }

            // Reiniciar el temporizador
            contadorTiempo = 0;
            temporizador.Start();
        }

        private void Temporizador_Elapsed(object sender, ElapsedEventArgs e)
        {
            contadorTiempo++;
            Device.BeginInvokeOnMainThread(() =>
            {
                lblTemporizador.Text = $"Tiempo: {contadorTiempo}s";
            });
        }

        private void TxtRespuesta_Completed(object sender, EventArgs e)
        {
            intentos++;
            if (int.TryParse(txtRespuesta.Text, out int respuestaUsuario) && respuestaUsuario == respuesta)
            {
                correctos++;
            }

            lblIntentos.Text = $"Intentos: {intentos}";
            lblCorrectos.Text = $"Correctos: {correctos}";

            txtRespuesta.Text = string.Empty;
            BtnIniciar_Clicked(sender, e);
        }

        private void BtnSalir_Clicked(object sender, EventArgs e)
        {
            Environment.Exit(0);
        }
    }
}

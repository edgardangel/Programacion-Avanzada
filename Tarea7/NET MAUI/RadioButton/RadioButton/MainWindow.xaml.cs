using System;
using System.Windows;

namespace RadioButton
{
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void btnCalcular_Click(object sender, RoutedEventArgs e)
        {
            double a, b, r = 0;

            // Validar que los textos sean números
            if (!double.TryParse(txtA.Text, out a) || !double.TryParse(txtB.Text, out b))
            {
                MessageBox.Show("Por favor, ingrese valores numéricos válidos.");
                return;
            }

            // Verificar qué operación está seleccionada
            if (rbSuma.IsChecked == true)
            {
                r = a + b;
            }
            else if (rbResta.IsChecked == true)
            {
                r = a - b;
            }
            else if (rbMulti.IsChecked == true)
            {
                r = a * b;
            }
            else if (rbDiv.IsChecked == true)
            {
                if (b == 0)
                {
                    MessageBox.Show("No se puede dividir por cero.");
                    return;
                }
                r = a / b;
            }

            // Mostrar resultado
            txtResultado.Text = r.ToString();
        }

        private void btnConvertir_Click(object sender, RoutedEventArgs e)
        {
            double a, conversion = 0;

            if (!double.TryParse(txtA.Text, out a))
            {
                MessageBox.Show("Ingrese un valor numérico válido para convertir.");
                return;
            }

            if (rbCmPulg.IsChecked == true)
            {
                conversion = a / 2.54;
            }
            else if (rbPulgCm.IsChecked == true)
            {
                conversion = a * 2.54;
            }

            txtResultado.Text = conversion.ToString("0.00");
        }
    }
}
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

namespace CheckBoxx
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void btnCalcular_Click(object sender, RoutedEventArgs e)
        {
            int cantidad = Convert.ToInt32(txtCantidad.Text);

            double total = 25; 
            if (chkCebolla.IsChecked == true)
                total += 1;

            if (chkJitomate.IsChecked == true)
                total += 3.5;

            if (chkPapas.IsChecked == true)
                total += 12.35;

            double GranTotal = total * cantidad;

            txbTotal.Text = "$ " + GranTotal.ToString();
        }
    }
}
using Microsoft.Maui.Controls;

namespace a2233336126_tareas_unidad01 // Este namespace debe coincidir con el del archivo XAML
{
    public partial class EjemploJframe_03_d : ContentPage
    {
        public EjemploJframe_03_d()
        {
            InitializeComponent();  // Aseg�rate de que InitializeComponent() est� aqu�
        }

        private void OnCalcularClicked(object sender, EventArgs e)
        {
            Lb.Text = "El �rea del tri�ngulo es 5";
        }
    }
}
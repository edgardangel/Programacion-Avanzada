using System;

namespace AppTarea9
{
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
        }

        private void HelloButton_Clicked(object sender, EventArgs e)
        {
            ResultLabel.Text = "Hola!";
        }

        private void GoodbyeButton_Clicked(object sender, EventArgs e)
        {
            ResultLabel.Text = "Adiós!";
        }

        private void OKButton_Clicked(object sender, EventArgs e)
        {
            string name = NameEntry.Text;
            string occupation = OccupationEntry.Text;

            if (!string.IsNullOrEmpty(name) && !string.IsNullOrEmpty(occupation))
            {
                ResultLabel.Text = $"Persona: {name}, Ocupación: {occupation}";
            }
            else
            {
                ResultLabel.Text = "Por favor, introduce un nombre y una ocupación.";
            }
        }
    }
}

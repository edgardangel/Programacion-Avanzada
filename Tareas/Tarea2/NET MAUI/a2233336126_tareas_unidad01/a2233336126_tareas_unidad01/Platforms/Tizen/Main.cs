using System;
using Microsoft.Maui;
using Microsoft.Maui.Hosting;

namespace a2233336126_tareas_unidad01
{
    internal class Program : MauiApplication
    {
        protected override MauiApp CreateMauiApp() => MauiProgram.CreateMauiApp();

        static void Main(string[] args)
        {
            var app = new Program();
            app.Run(args);
        }
    }
}

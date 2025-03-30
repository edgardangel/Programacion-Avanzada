using System;
using System.Windows;
using System.Windows.Threading;

namespace DualModeStopwatch
{
    public partial class MainWindow : Window
    {
        private DispatcherTimer runningTimer;
        private long startTime;
        private long runningStartTime;
        private long totalElapsedTime;
        private long runningElapsedTime;
        private bool isRunning;

        public MainWindow()
        {
            InitializeComponent();
            runningTimer = new DispatcherTimer();
            runningTimer.Interval = TimeSpan.FromSeconds(1);
            runningTimer.Tick += RunningTimer_Tick;
        }

        private void RunningTimer_Tick(object sender, EventArgs e)
        {
            long currentTime = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
            runningElapsedTime = currentTime - runningStartTime;
            UpdateRunningTimeField();

            totalElapsedTime = currentTime - startTime;
            UpdateTotalTimeField();
        }

        private void StartButton_Click(object sender, RoutedEventArgs e)
        {
            if (!isRunning)
            {
                isRunning = true;
                if (startTime == 0)
                {
                    startTime = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
                }
                runningStartTime = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
                runningTimer.Start();
                startButton.Content = "Stop";
                resetButton.IsEnabled = false;
                exitButton.IsEnabled = false;
            }
            else
            {
                isRunning = false;
                runningTimer.Stop();
                startButton.Content = "Restart";
                resetButton.IsEnabled = true;
                exitButton.IsEnabled = true;
            }
        }

        private void ResetButton_Click(object sender, RoutedEventArgs e)
        {
            isRunning = false;
            runningTimer.Stop();
            startTime = 0;
            runningStartTime = 0;
            totalElapsedTime = 0;
            runningElapsedTime = 0;
            UpdateRunningTimeField();
            UpdateTotalTimeField();
            resetButton.IsEnabled = false;
        }

        private void ExitButton_Click(object sender, RoutedEventArgs e)
        {
            Application.Current.Shutdown();
        }

        private void UpdateRunningTimeField()
        {
            runningTimeField.Text = FormatTime(runningElapsedTime);
        }

        private void UpdateTotalTimeField()
        {
            totalTimeField.Text = FormatTime(totalElapsedTime);
        }

        private string FormatTime(long timeInMillis)
        {
            long seconds = (timeInMillis / 1000) % 60;
            long minutes = (timeInMillis / (1000 * 60)) % 60;
            long hours = timeInMillis / (1000 * 60 * 60);
            return string.Format("{0:D2}:{1:D2}:{2:D2}", hours, minutes, seconds);
        }
    }
}
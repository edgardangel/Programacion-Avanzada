using CommunityToolkit.Mvvm.ComponentModel;
using System.Collections.ObjectModel;
using System.Threading.Tasks; // Necesario para Task

namespace MovieCatalog.ViewModels
{
    public class MovieListViewModel : ObservableObject
    {
        private MovieViewModel _selectedMovie;

        public ObservableCollection<MovieViewModel> Movies { get; set; }

        public MovieViewModel SelectedMovie
        {
            get => _selectedMovie;
            set => SetProperty(ref _selectedMovie, value);
        }

        public MovieListViewModel()
        {
            Movies = new ObservableCollection<MovieViewModel>();
        }

        public async Task RefreshMovies()
        {
            IEnumerable<Models.Movie> moviesData = await Models.MoviesDatabase.GetMovies();

            Movies.Clear(); // Limpiar para evitar duplicados al actualizar
            foreach (Models.Movie movie in moviesData)
            {
                Movies.Add(new MovieViewModel(movie));
            }
        }

        public void DeleteMovie(MovieViewModel movie)
        {
            Movies.Remove(movie);
        }
    }
}

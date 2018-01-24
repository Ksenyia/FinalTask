package by.tr.web.controller.command;

import by.tr.web.controller.ActionCommand;

public enum CommandEnum {
	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	REGISTER {
		{
			this.command = new RegisterCommand();
		}
	},
	MOVIES {
		{
			this.command = new MoviesCommand();
		}
	},
	MOVIE{
		{
			this.command = new MovieCommand();
		}
	},
	RATING {
		{
			this.command = new RatingCommand();
		}
	},
	SEARCH {
		{
			this.command = new SearchCommand();
		}
	},
	LOCAL {
		{
			this.command = new LocalCommand();
		}
	},
	USERS {
		{
			this.command = new UsersCommand();
		}
	},
	EDITUSERS {
		{
			this.command = new EditCommand();
		}
	},
	ADDMOVIE {
		{
			this.command = new AddMovieCommand();
		}
	},
	EDITMOVIE {
		{
			this.command = new EditMovieCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},
	SENDCHANGES {
		{
			this.command = new SendChangesCommand();
		}
	};
	ActionCommand command;
	public ActionCommand getCurrentCommand() {
		return command;
	}
}

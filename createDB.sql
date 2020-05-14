Create Database FootballManagementDB1;
GO
USE [FootballManagementDB1]
GO

CREATE TABLE [Admins](
	[ID] [char](30)  Primary key,
);

CREATE TABLE [Passwords](
	[ID] [char](30)  Primary key,
	[Password] [char](255) ,

);

CREATE TABLE [Users](
	[ID] [char](30)  Primary key,
	[FirstName] [varchar](255) ,
	[LastName] [varchar](255) ,
	[Mail] [varchar](255) ,
	[isActive] [bit] ,
	[Roles] [varchar](255) ,
	[searchHistories] [varchar](1000) ,
	--FOREIGN KEY (ID) REFERENCES [Passwords] (ID)
	FOREIGN KEY([ID]) REFERENCES [dbo].[Passwords] ([ID]) ON UPDATE CASCADE ON DELETE CASCADE ,
);

CREATE TABLE [Referees](
	[ID] [char](30)  Primary key,
	[Training] [varchar](50) ,
	[Games] [varchar](255) ,
);

CREATE TABLE [UnionRepresentatives](
	[ID] [char](30)  Primary key,
);

CREATE TABLE [Coaches](
	[ID] [char](30)  Primary key,
	[Training] [varchar](50) ,
	[Teams] [varchar](255) ,
	[isActive] [bit] ,
	[Price] [real] ,
);

CREATE TABLE [Fans](
	[ID] [char](30)  Primary key,
	[Address] [varchar](255) ,
	[Phone] [varchar](50) ,
	[FollowedPagesIDs] [varchar](255) ,
);

CREATE TABLE [Fields](
	[ID] [char](30)  Primary key,
	[Location] [char](50) ,
	[Capacity] [int] ,
	[Teams] [varchar](255) ,
	[isActive] [bit] ,
	[Price] [real] ,
);

CREATE TABLE [Players](
	[ID] [char](30)  Primary key,
	[Birthdate] [date] ,
	[Teams] [varchar](255) ,
	[isActive] [bit] ,
	[Price] [real] ,
);

CREATE TABLE [TeamManagers](
	[ID] [char](30)  Primary key,
	[Teams] [varchar](255) ,
	[isActive] [bit] ,
	[Price] [real] ,
	[ManageAssets] [bit] ,
	[Finance] [bit] ,
);

CREATE TABLE [TeamOwners](
	[ID] [char](30)  Primary key,
	[Teams] [varchar](255) ,
	[ClosedTeams] [varchar](255) ,
	[AppointedTeamOwners] [varchar] ,
	[AppointedTeamManagers] [varchar](255) ,
);

CREATE TABLE [PersonalPages](
	[ID] [char](30)  Primary key,
	[OwnerID] [char](30) ,
	[PageData] [varchar](max) ,
	[Followers] [varchar](max) ,
);

CREATE TABLE [Teams](
	[ID] [char](30)  Primary key,
	[Name] [varchar](50) ,
	[Wins] [int] ,
	[Losses] [int] ,
	[Draws] [int] ,
	[PersonalPageID] [char] (30) ,
	[TeamOwners] [varchar](255) ,
	[TeamManagers] [varchar](255) ,
	[Players] [varchar](255) ,
	[Coaches] [varchar](255) ,
	[Budget] [real] ,
	[GamesIDs] [varchar] (255) ,
	[Fields] [varchar] (255) ,
	[LeaguesInSeasons] [varchar] (255) ,
	[isActive] [bit] ,
	[isPermanentlyClosed] [bit] ,
);

CREATE TABLE [Leagues](
	[ID] [char](30)  Primary key,
	[Name] [varchar](50) ,
	[LeagueLevel] [varchar](50) ,
	[SeasonsIDs] [varchar](255) ,
);

CREATE TABLE [Seasons](
	[ID] [char](30)  Primary key,
	[SeasonYear] [int] ,
	[StartDate] [date] ,
	[LeaguesIDs] [varchar](255)
);

CREATE TABLE [LeaguesInSeasons](
	[ID] [char](30)  Primary key,
	[AssignmentPolicy] [char](255) ,
	[ScorePolicy] [char](255) ,
	[GamesIDs] [varchar](255) ,
	[RefereesIDs] [varchar](255) ,
	[TeamsIDs] [varchar](255) ,
	[RegistrationFee] [real] ,
	[Records] [varchar](1000) ,
);

CREATE TABLE [Complaints](
	[ID] [char](30)  Primary key,
	[ComplaintDate] [date] ,
	[isActive] [bit] ,
	[Description] [varchar] (1000) ,
	[ComplainedFanID] [char](50) ,
);

CREATE TABLE [Games](
	[ID] [char](30)  Primary key,
	[GameDate] [date] ,
	[HostScore] [int] ,
	[GuestScore] [int] ,
	[FieldID] [char] (50) ,
	[MainRefereeID] [char] (50) ,
	[SideRefereesIDs] [char] (50) ,
	[HostTeamID] [char] (50) ,
	[GuestTeamID] [char] (50) ,
	[AlertsFansIDs] [varchar](max) ,
	[EventReportID] [char] (50) ,
	[LeagueInSeasonID] [char] (50) ,
);

CREATE TABLE [EventReports](
	[ID] [char](30)  Primary key,
	[GameID] [char](30) ,
	[EventsIDs] [varchar](max) ,
);

CREATE TABLE [OfflineUsersNotifications](
	[ID] [char](30)  Primary key,
	[Notifications] [varchar](max) ,
);

CREATE TABLE [Events](
	[ID] [char](30)  Primary key,
	[EventType] [char](50) ,
	[EventDate] [date] ,
	[MinutesInGame] [real] ,
	[Description] [varchar](max) ,
);
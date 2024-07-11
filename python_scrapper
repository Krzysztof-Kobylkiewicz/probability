from bs4 import BeautifulSoup
import requests
import unicodedata
from datetime import datetime

url_post_tournament = 'http://localhost:8080/api/v1/tournament/add'
url_post_ranking = 'http://localhost:8080/api/v1/ranking/add'
url_post_matches = 'http://localhost:8080/api/v1/match/add'

# range(1,10) - all teams in ranking/ range(1,2) - first 25 teams
ranking_fixed_parameter = 10

# Script started at:
start_date = datetime.now()

# Request header:
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36'
}

ec_names = ["EURO 2012", "EURO 2016", "EURO 2020"]

teams_participating = [16, 24, 24]

ec_years = [12, 16, 20]

start_dates = ['08-06-2012', '10-06-2016', '11-06-2021']

end_dates = ['01-07-2012', '10-07-2016', '11-07-2021']

winner = ['Hiszpania', 'Portugalia', 'Włochy']

print(f"Sending tournaments to database.\n")
for i, v in enumerate(ec_names):
    tournament_data = {"name": ec_names[i],
                       "teamsParticipating": teams_participating[i],
                       "startDate": start_dates[i],
                       "endDate": end_dates[i],
                       "winner": winner[i],
                       "tournamentTypeId": 2}
    # print(f"\nTournament data: {tournament_data}")
    requests.post(url_post_tournament, json=tournament_data)

all_scraped_data_to_post, ranking_position, number_of_players, average_age, team_value, confederation, ranking_points = [], [], [], [], [], [], []
to_post = [ranking_position, number_of_players, average_age, confederation, ranking_points, team_value]

teams2024, teams2021, teams2016, teams2012 = [], [], [], []
sorted2024, sorted2021, sorted2016, sorted2012 = [], [], [], []
teams_from_ranking = [[] for _ in range(4)]
sorted_ranking = [[] for _ in range(4)]

datum = ['2024-06-20','2021-02-18', '2016-06-02', '2012-06-06']

# Downloading all teams from FIFA ranking:
print(f'\nDownloading all teams from FIFA Ranking is in progress. Iteration number (max = {ranking_fixed_parameter-1}):\n')
for j, v in enumerate(datum):
    for x in range(1, ranking_fixed_parameter):
        url_get_ranking = f'https://www.transfermarkt.pl/statistik/weltrangliste/statistik/stat/ajax/yw1/datum/{datum[j]}/plus/0/galerie/0/page/{x}'
        response = requests.get(url_get_ranking, headers=headers)
        soup_html = BeautifulSoup(response.text, 'html.parser')
        td_markups = soup_html.find_all('td', class_='hauptlink')
        for i in range(0, len(td_markups)):
            if not td_markups[i].find_all('a'):
                continue
            a_tags = td_markups[i].find_all('a')
            for a in a_tags:
                if not a.text:
                    continue
                teams_from_ranking[j].append(a.text)
    print(f'\nNumber of teams which have been scraped from {datum[j]} FIFA Ranking: {len(teams_from_ranking[j])}.')
    sorted_ranking[j] = list(teams_from_ranking[j])

# Replenishing the list with currently not existing teams which took part in the Worl Cup or Euro in the past:
missing_teams = ['Jugosławia', 'ZSRR', 'ČSR', 'Zair', 'Niemcy Wschodni', 'Serbia-Czarno.']

for index, value in enumerate(sorted_ranking):
    for missing_team in missing_teams:
        sorted_ranking[index].append(missing_team)

for i, v in enumerate(sorted_ranking):
    sorted_ranking[i].sort()

second_counter = 0
empty_points = None

for index, value in enumerate(sorted_ranking[0]):
    print(f"{value} - index: {index+1}")

print(f"\nIts length: {len(sorted_ranking[0])}\n")

print(f'Downloading all FIFA Ranking details is in progress.\n')
for j, v in enumerate(datum):
    ranking_position, confederation, ranking_points = [], [], []
    to_post = [ranking_position, confederation, ranking_points]
    second_counter = 0
    for x in range(1, ranking_fixed_parameter):
        # Using BeautifulSoup library:
        url_get_ranking = f'https://www.transfermarkt.pl/statistik/weltrangliste/statistik/stat/ajax/yw1/datum/{datum[j]}/plus/0/galerie/0/page/{x}'
        response = requests.get(url_get_ranking, headers=headers)
        soup_html = BeautifulSoup(response.text, 'html.parser')
        td_markups = soup_html.find_all('td', class_='hauptlink')
        td_markups_zentriert = soup_html.find_all('td', class_='zentriert')
        td_rechts = soup_html.find_all('td', class_='rechts')

        # For loop which scraps team value:
        for td in td_rechts:
            if td.text.split(sep=" ", maxsplit=1)[0].replace(",", ".") == "-":
                team_value.append(None)
            else:
                match td.text.split(sep=" ", maxsplit=1)[1]:
                    case 'tys. €':
                        team_value.append((float(td.text.split(sep=" ", maxsplit=1)[0].replace(",", ".")))/1000)
                    case 'mln €':
                        team_value.append(float(td.text.split(sep=" ", maxsplit=1)[0].replace(",", ".")))
                    case 'mld €':
                        team_value.append((float(td.text.split(sep=" ", maxsplit=1)[0].replace(",", ".")))*1000)

        counter = 0

        # For loop which scraps remaining data excluding squad value:
        for y, value in enumerate(td_markups_zentriert):
            match counter:
                case 0:
                    ranking_position.append(unicodedata.normalize("NFKD", td_markups_zentriert[y].text).replace(" ", ""))
                    counter += 1
                case 1:
                    confederation.append(td_markups_zentriert[y].text)
                    counter += 1
                case 2:
                    if td_markups_zentriert[y].text == '-':
                        ranking_points.append(empty_points)
                    else:
                        ranking_points.append(td_markups_zentriert[y].text)
                    counter += 1
            if y % 3 == 2:
                data_ranking = {'teamId': sorted_ranking[0].index(teams_from_ranking[j][second_counter])+1,
                                'position': to_post[0][second_counter],
                                'confederation': to_post[1][second_counter],
                                'points': to_post[2][second_counter],
                                'rankingDate': datum[j]
                                }
                if datum[j] != '2024-04-04':
                    post_ranking_request = requests.post(url_post_ranking, json=data_ranking)
                    if post_ranking_request.status_code == 201:
                        print(f'\nRanking details have been successfully sent to database: {data_ranking}.\n')
                    else:
                        print(f'Ranking details have not been created. Http status code: {post_ranking_request.status_code}.')
                counter = 0
                second_counter += 1

match_course_hrefs = []

# Range (13, len(wm_years)) - World Cups 2006-2022:
print(f'\nDownloading details about matches played during European Championship 2012-2020. Iteration number (max = 3):\n')
for i, year in enumerate(ec_years):
    # URL for http get method:
    print(f"{i}", end=" ")
    url_get = f'https://www.transfermarkt.pl/europameisterschaft-20{year}/gesamtspielplan/pokalwettbewerb/EM{year}/saison_id/20{year}'

    host_names, host_goals, away_goals, away_names = [], [], [], []

    # HTML parsing:
    response = requests.get(url_get, headers=headers)
    soup = BeautifulSoup(response.text, 'lxml')
    teams = soup.find_all('td', class_='text-right')
    scores = soup.find_all('a', class_='ergebnis-link')
    tables_without_class = soup.find_all('table', class_=False)

# Loop which creates array of host names:
    for team in teams:
        host_name = team.find('a').text
        host_names.append(host_name)

# Removing duplicates from array and sorting in alphabetical order:
    teams_set = list(set(host_names))
    teams_sorted_list = teams_set.sort()

    for score in scores:
        scoreline = score.text
        match_course_hrefs.append(score['href'])
        host_goals.append(scoreline[0])
        away_goals.append((scoreline[2]))

    for table in tables_without_class:
        td_tags = table.find_all('td', class_=['no-border-links hauptlink', 'no-border-links hauptlink bg_gelb_20'])
        for td in td_tags:
            a_tag = td.find('a').text
            away_names.append(a_tag)

    scraped = [host_names, host_goals, away_goals, away_names]
    all_scraped_data_to_post.append(scraped)

all_div_tags_statistics = []

start_time_statistics = datetime.now()
char = "."
# Obtaining match statistics:
print(f'')
print('\nObtaining match statistics.\n')
print(char*133)
for i in range(0, len(match_course_hrefs)):
    print(f'.', end="")
    part_of_the_link = str('https://www.transfermarkt.pl')
    match_course_hrefs[i] = (str(part_of_the_link) + str(match_course_hrefs[i])).replace('index', 'statistik')
    response = requests.get(match_course_hrefs[i], headers=headers)
    stst_soup = BeautifulSoup(response.text, 'html.parser')
    temporary = stst_soup.find_all('div', class_='sb-statistik-zahl')
    all_div_tags_statistics.append(temporary)

total_attempts, attempts_off_target, paraden, corners, free_kicks, fouls, offsides = [], [], [], [], [], [], []

for i in range(0, len(match_course_hrefs)):
    for j in range(0, len(all_div_tags_statistics[i])):
        if j < 2:
            total_attempts.append(all_div_tags_statistics[i][j].text)
        elif j < 4:
            attempts_off_target.append(all_div_tags_statistics[i][j].text)
        elif j < 6:
            paraden.append(all_div_tags_statistics[i][j].text)
        elif j < 8:
            corners.append(all_div_tags_statistics[i][j].text)
        elif j < 10:
            free_kicks.append(all_div_tags_statistics[i][j].text)
        elif j < 12:
            fouls.append(all_div_tags_statistics[i][j].text)
        elif j < 14:
            offsides.append(all_div_tags_statistics[i][j].text)

end_time_statistics = datetime.now() - start_time_statistics
duration_statistics = round(end_time_statistics.seconds/60, 2)
print(f'\nObtaining statistics finished. Execution time: {duration_statistics} [min].\n')

counter4 = 0

# Sending match details to database:
print(f"Sending match details to database is in progress.\n")
counter_for_tournament_id = 18
for x in range(0, len(all_scraped_data_to_post)):
    counter_for_tournament_id += 1
    for y in (range(0, len(all_scraped_data_to_post[x][0]))):
        winnerId = 0
        if all_scraped_data_to_post[x][1][y] > all_scraped_data_to_post[x][2][y]:
            winnerId = int(sorted_ranking[0].index(all_scraped_data_to_post[x][0][y]))+1
        elif all_scraped_data_to_post[x][1][y] < all_scraped_data_to_post[x][2][y]:
            winnerId = int(sorted_ranking[0].index(all_scraped_data_to_post[x][3][y]))+1
        else:
            winnerId = None

        print(f"HOST ID: {int(sorted_ranking[0].index(all_scraped_data_to_post[x][0][y]))+1}")
        print(f"AWAY ID: {int(sorted_ranking[0].index(all_scraped_data_to_post[x][3][y]))+1}")
        print(f"T ID: {counter_for_tournament_id}")

        data = {'hostTeamGoals': all_scraped_data_to_post[x][1][y],
                'awayTeamGoals': all_scraped_data_to_post[x][2][y],
                'hostId': int(sorted_ranking[0].index(all_scraped_data_to_post[x][0][y]))+1,
                'awayId': int(sorted_ranking[0].index(all_scraped_data_to_post[x][3][y]))+1,
                'winnerId': winnerId,
                'hostAttemptsOnTarget': total_attempts[counter4],
                'awayAttemptsOnTarget': total_attempts[counter4 + 1],
                'hostAttemptsOffTarget': attempts_off_target[counter4],
                'awayAttemptsOffTarget': attempts_off_target[counter4 + 1],
                'hostParaden': paraden[counter4],
                'awayParaden': paraden[counter4 + 1],
                'hostCorners': corners[counter4],
                'awayCorners': corners[counter4 + 1],
                'hostFreeKicks': free_kicks[counter4],
                'awayFreeKicks': free_kicks[counter4 + 1],
                'hostFouls': fouls[counter4],
                'awayFouls': fouls[counter4 + 1],
                'hostOffsides': offsides[counter4],
                'awayOffsides': offsides[counter4 + 1],
                'tournamentId': counter_for_tournament_id
                }
        counter4 += 2
        print(f"Match details: {data}\n")
        response = requests.post(url_post_matches, json=data)
        print(f'Match number {counter4 + 1} created. Http status: {response.status_code}. {data}\n')

end_date = datetime.now() - start_date
duration = round(end_date.seconds/60, 2)
print(f'Script has finished. Execution time: {duration} [min]. Obtaining match statistics took {round(duration_statistics/duration, 2)*100}% of execution time.\n')

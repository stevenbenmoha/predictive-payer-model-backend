# Required Python Packages
import pandas as pd
import numpy as np
from plotly.graph_objs._figure import Figure
from sklearn import linear_model
from sklearn import metrics
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt

import plotly.graph_objs as go
import plotly.plotly as py
import plotly.offline
from plotly.graph_objs import *

DATASET_PATH = "/Users/stevenbenmoha/IdeaProjects/payer-model/payer-model/src/main/java/com/model/data/data.csv"

def scatter_with_color_dimension_graph(feature, target, layout_labels):
    """
    Scatter with color dimension graph to visualize the density of the
    Given feature with target
    :param feature:
    :param target:
    :param layout_labels:
    :return:
    """
    trace1 = go.Scatter(
        y=feature,
        mode='markers',
        marker=dict(
            size=16,
            color=target,
            colorscale='RdBu',
            showscale=True
        )
    )

    layout = go.Layout(
        title=layout_labels[2],
        xaxis=dict(title=layout_labels[0]), yaxis=dict(title=layout_labels[1]))
    data = [trace1]
    fig = Figure(data=data, layout=layout)
    plot1 = plotly.offline.plot(fig)
    py.image.save_as(fig, filename=layout_labels[1] + '_Deal.png')


def main():

    deal_data_headers = ["Channel",
                          "Customer",
                          "Segment/Plan",
                          "Deal_Level_of_Control",
                          "Therapeutic_Area",
                          "Product",
                          "WAC_Price",
                          "Start_Date",
                          "End_Date",
                          "2018_Status_Quo_Brand_Manufacturer",
                          "2018_Status_Quo_Tier",
                          "2018_Status_Quo_Placement",
                          "2018_Status_Quo_Restriction",
                          "Deal_Brand_Manufacturer",
                          "Deal_Tier",
                          "Deal_Placement",
                          "Deal_Restriction",
                          "Deal_Rebate",
                          "PP_Type",
                          "PP_Threshold",
                          "PP_Lookback_Date",
                          "2019_Actual_Brand_Manufacturer",
                          "2019_Actual_Tier",
                          "2019_Actual_Placement",
                          "2019_Actual_Restriction",
                          "Share_Shift",
                          "Decision"
]
    # Load the dataset
    deal_data = pd.read_csv(DATASET_PATH, names=deal_data_headers)

    X = deal_data.iloc[:,25]

    # y = target values, last column of the data frame
    y = deal_data.iloc[:,26]

    print(X)
    print(y)

    # filter out the deals that got approved
    approved = deal_data.loc[y == 1]

    # filter out the deals that din't get approved
    not_approved = deal_data.loc[y == 0]

    # plots

    plt.scatter(approved.iloc[:, 25], approved.iloc[:, 22], s=120, label='Approved', color='blue')
    plt.scatter(not_approved.iloc[:, 25], not_approved.iloc[:, 22], s=120, label='Rejected', color='red')
    plt.xlabel('Share Shift')
    plt.ylabel('Status')
    plt.legend()
    plt.show()


    # print ("Number of observations :: ", len(deal_data.index))
    # print ("Number of columns :: ", len(deal_data.columns))
    # print ("Headers :: ", deal_data.columns.values)
    # print ("deal_data_Share_Shift :: ", list(deal_data["Share_Shift"][:120]))
    # print("deal_data_Decision :: ", list(deal_data["Decision"][:120]))
    # Graph Labels
    # graph_labels = ["Number of Observations", "Decision", "Share_Shift"]

    # scatter_with_color_dimension_graph(list(deal_data["Decision"][:120])
                                       ,list(deal_data["Share_Shift"][:120]),
                                       graph_labels)

if __name__ == "__main__":
    main()



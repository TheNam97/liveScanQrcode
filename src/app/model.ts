
export interface IQrCode {
    id?: any | null;
    imgGoods?: any | null;
    imgQrcode?: any | null;
    itemCode?: any | null;
    poscode?: any | null;
    receiverAddress?: any | null;
    senderAddress?: any | null;
    time?: any | null;

    acceptancePOSCode?: any | null;
    weight?: any | null;
    receiverAddressCode?: any | null;
    serviceCode?: any | null;
    speaker?: any | null;
}
export class QrCode implements IQrCode {
}

